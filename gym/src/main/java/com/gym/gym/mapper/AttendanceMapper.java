package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.gym.gym.domain.Attendance;

@Alias("Attendance")
public interface AttendanceMapper {

    // 출석 조회
    public List<Attendance> list() throws Exception;





    // 출석 체크
    // 유저 검색
    // 출석 랭킹












}
