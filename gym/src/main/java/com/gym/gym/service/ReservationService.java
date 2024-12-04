package com.gym.gym.service;

import java.util.List;

import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.Reservation;

public interface ReservationService {
    
    // 예약 등록
    public int insert(Reservation reservation) throws Exception;

    // 예약 목록
    public List<Reservation> list(Option option, Page page) throws Exception;

    // 예약 상세 조회
    public Reservation read(int no) throws Exception;

    // 예약 취소(수정)
    public int cancel(Reservation reservation) throws Exception;

    public int count(Option option) throws Exception;

}