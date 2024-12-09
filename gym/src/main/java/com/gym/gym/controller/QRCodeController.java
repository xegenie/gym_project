package com.gym.gym.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.QRcode;
import com.gym.gym.domain.Users;
import com.gym.gym.service.QRCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/generate-qr-code")
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @PostMapping
    public ResponseEntity<byte[]> generateQRCode(@AuthenticationPrincipal CustomUser User) {
        // QRcode 객체 생성
        QRcode qrCode = new QRcode();


        Long no = User.getNo();
        log.info(no + "번호임");
        qrCode.setNo(no);  // 예시 값 
        qrCode.setUuid(UUID.randomUUID().toString());

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
