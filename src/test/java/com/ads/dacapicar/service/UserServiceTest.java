package com.ads.dacapicar.service;

import com.ads.dacapicar.entities.User;
import com.ads.dacapicar.entities.dto.request.UserRequestDTO;
import com.ads.dacapicar.entities.dto.response.UserResponseDTO;
import com.ads.dacapicar.exception.UserNotFoundException;
import com.ads.dacapicar.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void findAll_ShouldReturnUserList() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<UserResponseDTO> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    void findById_ShouldReturnUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        UserResponseDTO result = userService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void createUser_ShouldSaveUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("exemplo@email.com");
        userRequestDTO.setPassword("senhafortetestada123654");

        User mockUser = new User();
        mockUser.setId(1L);
        when(passwordEncoder.encode(userRequestDTO.getPassword())).thenReturn("ecodepassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        UserResponseDTO result = userService.createUser(userRequestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void createMultipleUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}