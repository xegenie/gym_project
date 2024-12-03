package com.gym.gym.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRcodeService {

    public byte[] generateQRCode(String qrId) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // QR 코드 생성 설정
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);  // QR 코드 마진 설정

        // QR 코드 생성 (입력된 qrId를 바탕으로 QR 코드 생성)
        BufferedImage qrCodeImage = createQRCodeImage(qrId, 200, 200, hints);

        // BufferedImage를 바이트 배열로 변환
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "PNG", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    private BufferedImage createQRCodeImage(String qrId, int width, int height, Map<EncodeHintType, Object> hints) throws WriterException {
        // QR 코드 생성
        com.google.zxing.common.BitMatrix bitMatrix = new QRCodeWriter().encode(qrId, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, (bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF));  // QR 코드의 픽셀 색상
            }
        }
        return image;
    }
}
