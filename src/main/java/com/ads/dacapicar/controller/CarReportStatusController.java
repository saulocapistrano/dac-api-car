package com.ads.dacapicar.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/cars/reports")
@SecurityRequirement(name = "bearerAuth")
public class CarReportStatusController {

    @GetMapping("/report/status")
    public ResponseEntity<?> getReportStatus() throws Exception {
        String filePath = "relatorio_carros.pdf";

        File file = new File(filePath);
        if (file.exists()) {
            Path pdfPath = Paths.get(filePath);
            byte[] pdfContents = Files.readAllBytes(pdfPath);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=relatorio_carros.pdf");

            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatório ainda não está pronto.");
        }
    }

    @GetMapping("/report/status/{id}")
    public ResponseEntity<?> getSingleCarReportStatus(@PathVariable Long id) throws Exception {
        String filePath = "relatorio_carro_" + id + ".pdf";

        File file = new File(filePath);
        if (file.exists()) {
            Path pdfPath = Paths.get(filePath);
            byte[] pdfContents = Files.readAllBytes(pdfPath);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=relatorio_carro_" + id + ".pdf");

            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatório do carro com ID " + id + " ainda não está pronto.");
        }
    }


}
