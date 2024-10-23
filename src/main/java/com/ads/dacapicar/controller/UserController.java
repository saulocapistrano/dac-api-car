package com.ads.dacapicar.controller;

import com.ads.dacapicar.entities.User;
import com.ads.dacapicar.entities.dto.request.PasswordChangeRequestDTO;
import com.ads.dacapicar.entities.dto.request.UserRequestDTO;
import com.ads.dacapicar.entities.dto.response.UserResponseDTO;
import com.ads.dacapicar.exception.UserNotFoundException;
import com.ads.dacapicar.repository.UserRepository;
import com.ads.dacapicar.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO newUser = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<UserResponseDTO>> createMultipleUsers(@RequestBody List<UserRequestDTO> userRequestDTOList) {
        List<UserResponseDTO> newUsers = userService.createMultipleUsers(userRequestDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDTO passwordChangeRequestDTO, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        userService.changePassword(user.getId(), passwordChangeRequestDTO);

        return ResponseEntity.ok("Senha alterada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
