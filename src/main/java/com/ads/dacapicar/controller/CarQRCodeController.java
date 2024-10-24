package com.ads.dacapicar.controller;

import com.ads.dacapicar.entities.dto.response.CarResponseDTO;
import com.ads.dacapicar.service.CarService;
import com.ads.dacapicar.service.QRCodeService;
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
@RequestMapping("/api/qrcode")
public class CarQRCodeController {

    @Autowired
    private CarService carService;

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> generateCarQRCode(@PathVariable Long id) throws Exception {
        CarResponseDTO car = carService.findById(id);

        String carInfo = "Carro: " + car.getName() + "\n" +
                "Preço: " + car.getPrice() + "\n" +
                "Ano: " + car.getYear() + "\n" +
                "Cidade: " + car.getCity() + "\n" +
                "Placa: " + car.getPlaca() + "\n" +
                "Empresa: " + car.getCompany();

        String filePath = "car_qr_" + id + ".png";

        qrCodeService.generateQRCode(carInfo, 350, 350, filePath);

        Path path = Paths.get(filePath);
        byte[] qrCodeImage = Files.readAllBytes(path);

        return ResponseEntity.ok().header("Content-Type", "image/png").body(qrCodeImage);
    }
}
