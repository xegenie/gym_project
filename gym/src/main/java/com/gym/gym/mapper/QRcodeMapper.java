package com.gym.gym.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gym.gym.domain.QRcode;

@Mapper
public interface QRcodeMapper {

    // 출석 체크 ( 등록 )
    int insertQRcode(QRcode qRcode) throws Exception;

}
