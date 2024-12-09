package com.gym.gym.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.Reservation;
import com.gym.gym.domain.Users;

public interface ReservationService {
    
    // 예약 등록
    public int insert(Reservation reservation) throws Exception;

    // 예약 목록
    public List<Reservation> list(Option option, Page page) throws Exception;

    // 예약 상세 조회
    public Reservation read(int no) throws Exception;

    // 예약 취소(수정)
    public int cancel(Reservation reservation) throws Exception;

    // 트레이너 리스트
    public List<Users> trainerUsers() throws Exception;

    // 트레이너별 예약 목록
    public List<Reservation> sortByTrainer(@Param("option") Option option) throws Exception;

    // 날짜별 예약 개수
    public List<Map<String, Object>> countByDate(Option option) throws Exception;

    public int count(Option option) throws Exception;

    public List<Reservation> selectByStartEnd (int userNo, Date startTime, Date endTime) throws Exception;

}