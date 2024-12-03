package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gym.gym.domain.Reservation;
import com.gym.gym.service.ReservationService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 트레이너 리스트  
    @GetMapping("/user/trainerList")
    public String trainerList() {
        return "/user/reservation/trainerList";
    }
    
    // 마이페이지 예약 목록 화면
    @GetMapping("/user/myPage/ptList")
    public String userReservationList(Model model) throws Exception {
        List<Reservation> reservationList = reservationService.list();
        model.addAttribute("reservationList", reservationList);
        return "/user/myPage/ptList";
    }
    
    // 관리자 예약 목록 화면
    @GetMapping("/admin/reservation/list")
    public String adminReservationList(Model model) throws Exception {
        List<Reservation> reservationList = reservationService.list();
        model.addAttribute("reservationList", reservationList);
        return "/admin/reservation/list";
    }

    // 관리자 캘린더 화면
    @GetMapping("/admin/reservation/calendar")
    public String adminCalendar() {
        return "/admin/reservation/calendar";
    }
    
    // 예약 등록 화면
    @GetMapping("/user/reservation/reservation")
    public String insert() {
        return "/user/reservation/reservation";
    }
    
    // 예약 등록 처리
    @PostMapping("/user/reservation/reservation")
    public String insertPro(Reservation reservation) throws Exception {
        log.info("예약 되나? : " + reservation);
        int result = reservationService.insert(reservation);
        if (result > 0) {
            return "redirect:/user/myPage/ptList";
        }
        return "redirect:/user/reservation/reservation?error";
    }
    
    // 회원이 예약 취소(수정)
    @PostMapping("/user/myPage/ptList")
    public String cancelUser(Reservation reservation) throws Exception {
        // if (reservation.getEnabled() == 1) {
            int result = reservationService.cancel(reservation);
            if (result > 0) {
                return "redirect:/user/myPage/ptList";
            }
        // }
        return "redirect:/user/myPage/ptList?error";
    }

    // 관리자가 예약 취소(수정)
    @PostMapping("/admin/reservation/list")
    public String cancelAdmin(Reservation reservation) throws Exception {
        int result = reservationService.cancel(reservation);
        if (result > 0) {
            return "redirect:/admin/reservation/list";
        }
        return "redirect:/admin/reservation/list?error";
    }
    
    
}