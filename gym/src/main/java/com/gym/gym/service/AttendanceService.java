package com.gym.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.gym.domain.Attendance;

@Service
public interface AttendanceService {

    // 출석 내역 조회
    List<Attendance> list() throws Exception;

    // 출석 체크 ( 등록 )
    int insertAttendance(Attendance attendance) throws Exception;

    // 유저 출석 내역 조회
    List<Attendance> getAttendanceByUserNo(int userNo) throws Exception;

    // 출석 랭킹 조회
    List<Attendance> getAttendanceRanking() throws Exception;

    // 출석 내역 검색 (유저 번호와 키워드로)
    List<Attendance> searchAttendance(String searchKeyword) throws Exception;
   
 
}
