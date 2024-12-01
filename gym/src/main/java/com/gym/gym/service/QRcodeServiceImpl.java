package com.gym.gym.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gym.gym.domain.QRcode;
import com.gym.gym.mapper.QRcodeMapper;

public class QRcodeServiceImpl implements QRcodeService {

    @Autowired
    private QRcodeMapper qRcodeMapper;

    // QR 코드 발급 ( 생성 )
    @Override
    public int insertQRcode(QRcode qRcode) throws Exception {
        return qRcodeMapper.insertQRcode(qRcode);
    }

}
