package com.gym.gym.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.QRcode;
import com.gym.gym.domain.Users;
import com.gym.gym.service.QRCodeGenerator;

@Controller
@RequestMapping("/generate-qr-code")
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @PostMapping
    public ResponseEntity<byte[]> generateQRCode() {
        // QRcode 객체 생성
        QRcode qrCode = new QRcode();

        Users users = new Users();

        qrCode.setNo(8989);  // 예시 값 
        qrCode.setUserNo(123);
        qrCode.setUuid(UUID.randomUUID().toString());
        qrCode.setCreatedAt(new Date());

        ByteArrayOutputStream qrCodeOutputStream = new ByteArrayOutputStream();
        try {
            qrCodeGenerator.generateQRCodeImage(qrCode, qrCodeOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        byte[] imageBytes = qrCodeOutputStream.toByteArray();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}
