package com.gym.gym.service;

import org.springframework.stereotype.Service;

import com.gym.gym.domain.QRcode;

@Service
public interface QRcodeService {

    // QR 코드 발급 ( 생성 )
    int insertQRcode(QRcode qRcode) throws Exception;

}
