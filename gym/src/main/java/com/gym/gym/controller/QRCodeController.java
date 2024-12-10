package com.gym.gym.controller;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.QRcode;
import com.gym.gym.service.QRCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/generate-qr-code")
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @PostMapping
    public String generateQRCode(@AuthenticationPrincipal CustomUser user, Model model) {
        QRcode qrCode = new QRcode();

        Long no = user.getNo();
        qrCode.setNo(no); // Users 테이블에서 no 받은 후 Qrcode에 세팅
        qrCode.setUuid(UUID.randomUUID().toString());

        ByteArrayOutputStream qrCodeOutputStream = new ByteArrayOutputStream();
        try {
            qrCodeGenerator.generateQRCodeImage(qrCode, qrCodeOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "QR 코드 생성 중 오류가 발생했습니다.");
            return "error";
        }

        byte[] imageBytes = qrCodeOutputStream.toByteArray();
        String qrCodeBase64 = Base64.getEncoder().encodeToString(imageBytes);

        model.addAttribute("qrCodeBase64", qrCodeBase64);
        return "qrCodePage"; 
    }
}
