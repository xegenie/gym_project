package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.Attendance;
import com.gym.gym.mapper.AttendanceMapper;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    // 유저 출석 내역 조회
    @Override
    public List<Attendance> list() throws Exception {
        return attendanceMapper.list();
    }

    // 출석 체크 ( 등록 )
    @Override
    public int insertAttendance(Attendance attendance) throws Exception {
        return attendanceMapper.insertAttendance(attendance);
    }

    // 유저 출석 내역 조회
    @Override
    public List<Attendance> getAttendanceByUserNo(int userNo) throws Exception {
        return attendanceMapper.getAttendanceByUserNo(userNo);
    }

    // 출석 랭킹 조회
    @Override
    public List<Attendance> getAttendanceRanking() throws Exception {
        return attendanceMapper.getAttendanceRanking();
    }

    // AttendanceServiceImpl.java 수정
    @Override
    public List<Attendance> searchAttendance(String searchKeyword) throws Exception {
        return attendanceMapper.searchAttendance(searchKeyword);
    }

   

}
