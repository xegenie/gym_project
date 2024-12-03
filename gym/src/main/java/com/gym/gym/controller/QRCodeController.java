package com.gym.gym.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.service.QRCodeGenerator;

@Controller
@RequestMapping("/generate-qr-code")
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @PostMapping
    public ResponseEntity<byte[]> generateQRCode() {
        String qrText = "https://example.com"; // QR 코드에 포함할 텍스트 예시

        // QR 코드 이미지를 생성하여 ByteArray로 변환
        ByteArrayOutputStream qrCodeOutputStream = new ByteArrayOutputStream();
        try {
            qrCodeGenerator.generateQRCodeImage(qrText, qrCodeOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // 이미지 데이터 바이트 배열로 반환
        byte[] imageBytes = qrCodeOutputStream.toByteArray();

        // HTTP 응답에 이미지 데이터와 함께 200 OK 상태 코드 반환
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}

