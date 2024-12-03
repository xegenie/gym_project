package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.Reservation;
import com.gym.gym.service.ReservationService;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Controller
@RequestMapping("/user")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 트레이너 리스트  
    @GetMapping("/trainerList")
    public String trainerList() {
        return "/user/reservation/trainerList";
    }
    
    // 마이페이지 예약 목록 화면
    @GetMapping("/ptList")
    public String reservationList(Model model) throws Exception {
        List<Reservation> reservationList = reservationService.list();
        model.addAttribute("reservationList", reservationList);
        return "/user/myPage/ptList";
    }

    // 예약 등록 화면
    @GetMapping("/trainerReservation")
    public String insert() {
        return "/user/reservation/trainerReservation";
    }
    
    // 예약 등록 처리
    @PostMapping("/trainerReservation")
    public String insertPro(Reservation reservation) throws Exception {
        log.info("예약 되나? : " + reservation);
        int result = reservationService.insert(reservation);
        if (result > 0) {
            return "redirect:/user/ptList";
        }
        return "redirect:/user/trainerReservation?error";
    }
    
    // 예약 취소(수정)
    @PostMapping("/ptList")
    public String cancel(Reservation reservation) throws Exception {
        // if (reservation.getEnabled() == 1) {
            int result = reservationService.cancel(reservation);
            if (result > 0) {
                return "redirect:/user/ptList";
            }
        // }
        return "redirect:/user/ptList?error";
    }
    
}