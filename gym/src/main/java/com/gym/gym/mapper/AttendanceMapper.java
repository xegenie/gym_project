package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.gym.gym.domain.Attendance;

@Alias("Attendance")
public interface AttendanceMapper {

    // 출석 내역 조회
    List<Attendance> list() throws Exception;

    // 출석 체크 ( 등록 )
    int insertAttendance(Attendance attendance) throws Exception;

    // 유저 검색
    List<Attendance> getAttendanceByUserNo(int userNo) throws Exception;

    // 출석 랭킹
    List<Attendance> getAttendanceRanking() throws Exception;












}
