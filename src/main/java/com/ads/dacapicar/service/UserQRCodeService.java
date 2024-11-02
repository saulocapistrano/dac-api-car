package com.ads.dacapicar.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserQRCodeService {

    public void generateQRCode(String text, int width, int height, String filePath) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
        Path path = Path.of(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
