package com.ads.dacapicar.controller;

import com.ads.dacapicar.entities.dto.response.UserResponseDTO;
import com.ads.dacapicar.service.UserService;
import com.ads.dacapicar.service.UserQRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/qrcode/user")
public class UserQRCodeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserQRCodeService qrCodeService;

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> generateUserQRCode(@PathVariable Long id) throws Exception {
        UserResponseDTO user = userService.findById(id);

        String userInfo = "Nome: " + user.getName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "CPF: " + user.getCpf() + "\n" +
                "Telefone: " + user.getPhoneNumber() + "\n" +
                "Data de Nascimento: " + user.getBirthDate() + "\n" +
                "Tipo de Usuário: " + user.getTypeUser();

        String filePath = "user_qr_" + id + ".png";

        qrCodeService.generateQRCode(userInfo, 350, 350, filePath);

        Path path = Paths.get(filePath);
        byte[] qrCodeImage = Files.readAllBytes(path);

        return ResponseEntity.ok().header("Content-Type", "image/png").body(qrCodeImage);
    }
}
