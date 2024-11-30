package com.gym.gym.mapper;

import org.apache.ibatis.type.Alias;

import com.gym.gym.domain.QRcode;

@Alias("QRcode")
public interface QRcodeMapper {

    // 출석 체크 ( 등록 )
    int insertQRcode(QRcode qRcode) throws Exception;

}
