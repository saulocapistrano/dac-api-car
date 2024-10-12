package com.ads.dacapicar.service;

import com.ads.dacapicar.entities.User;
import com.ads.dacapicar.entities.dto.request.UserRequestDTO;
import com.ads.dacapicar.entities.dto.response.UserResponseDTO;
import com.ads.dacapicar.entities.map.UserMapper;
import com.ads.dacapicar.exception.UserNotFoundException;
import com.ads.dacapicar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        User user = UserMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    public List<UserResponseDTO> createMultipleUsers(List<UserRequestDTO> userRequestDTOList) {
        List<User> users = userRequestDTOList.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());

        users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));

        List<User> savedUsers = userRepository.saveAll(users);
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
}
