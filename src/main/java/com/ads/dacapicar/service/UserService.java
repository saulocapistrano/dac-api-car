package com.ads.dacapicar.service;

import com.ads.dacapicar.entities.User;
import com.ads.dacapicar.entities.dto.request.PasswordChangeRequestDTO;
import com.ads.dacapicar.entities.dto.request.UserRequestDTO;
import com.ads.dacapicar.entities.dto.response.UserResponseDTO;
import com.ads.dacapicar.entities.map.UserMapper;
import com.ads.dacapicar.exception.UserNotFoundException;
import com.ads.dacapicar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));
        return UserMapper.toDto(user);
    }



    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado: " + userRequestDTO.getEmail());
        }

        if (userRepository.findByCpf(userRequestDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado: " + userRequestDTO.getCpf());
        }


        User user = UserMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        sendWelcomeEmail(user);

        return UserMapper.toDto(user);
    }




    public List<UserResponseDTO> createMultipleUsers(List<UserRequestDTO> userRequestDTOList) {
        List<User> users = userRequestDTOList.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());

        users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));

        List<User> savedUsers = userRepository.saveAll(users);

        savedUsers.forEach(this::sendWelcomeEmail);

        return savedUsers.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }


    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setBirthDate(userRequestDTO.getBirthDate());
        user.setCpf(userRequestDTO.getCpf());
        user.setTypeUser(userRequestDTO.getTypeUser());

        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }

        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void changePassword(Long userId, PasswordChangeRequestDTO passwordChangeRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(passwordChangeRequestDTO.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Senha antiga incorreta.");
        }

        user.setPassword(passwordEncoder.encode(passwordChangeRequestDTO.getNewPassword()));
        userRepository.save(user);
    }

    private void sendWelcomeEmail(User user) {
        emailService.sendWelcomeEmail(
                user.getEmail(), "Bem vindo ao DAC-CAR",
                "olá" + user.getName() + ", seja bem vindo."
        );

    }
}
