package com.ads.dacapicar.entities.map;

import com.ads.dacapicar.entities.User;
import com.ads.dacapicar.entities.dto.request.UserRequestDTO;
import com.ads.dacapicar.entities.dto.response.UserResponseDTO;

import java.time.LocalDate;

public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setBirthDate(userRequestDTO.getBirthDate());
        user.setCpf(userRequestDTO.getCpf());
        user.setTypeUser(userRequestDTO.getTypeUser());
        user.setCreatedDate(LocalDate.now());

        return user;
    }

    public static UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.getCpf(),
                user.getTypeUser()
        );
    }
}
