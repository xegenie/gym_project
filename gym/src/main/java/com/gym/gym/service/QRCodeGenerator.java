package com.gym.gym.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.gym.gym.domain.QRcode;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeGenerator {

    public void generateQRCodeImage(QRcode qrCode, OutputStream outputStream) throws WriterException, IOException {
        String qrText = createQRText(qrCode);  // QR 코드에 포함할 텍스트 생성

        int width = 500;  // QR 코드 이미지의 가로 크기
        int height = 500; // QR 코드 이미지의 세로 크기
        String fileType = "png";  // 이미지 파일 타입

        // QR 코드 생성에 필요한 옵션들
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 1);  // 여백 설정

        // QR 코드 생성기
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height, hintMap);

        // BitMatrix를 BufferedImage로 변환
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        
        // QR 코드의 각 비트를 이미지의 픽셀로 설정
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }

        // 이미지를 outputStream으로 출력
        javax.imageio.ImageIO.write(image, fileType, outputStream);
    }

    private String createQRText(QRcode qrCode) {
        return String.format("http://localhost:8080/admin/attendance/check?qrcodeId=%d&uuid=%s",
                             qrCode.getNo(), qrCode.getUuid());
    }
    
}
