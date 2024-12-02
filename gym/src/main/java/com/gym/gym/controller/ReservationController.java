package com.gym.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.Reservation;
import com.gym.gym.service.ReservationService;

import lombok.extern.slf4j.Slf4j;





@Slf4j
@Controller
@RequestMapping("/gym")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 트레이너 리스트  
    @GetMapping("/trainerList")
    public String trainerList() {
        return "/user/reservation/trainerList";
    }
    
    
    // 예약 목록 화면
    // @GetMapping("/ptList")
    // public String reservationList() {
    //     return "/user/myPage/ptList";
    // }


    // @GetMapping("/ptList")
    // public String reservationListPro(Model model) throws Exception {
    //     List<Reservation> reservation = reservationService.list();
    //     model.addAttribute("reservation", reservation);
    //     return "/user/myPage/ptList";
    // }
    

    // 예약 등록 화면
    @GetMapping("/trainerReservation")
    public String insert() {
        return "/user/reservation/trainerReservation";
    }
    
    // 예약 등록 처리
    @PostMapping("/trainerReservation")
    public String insertPro(@ModelAttribute Reservation reservation) throws Exception {
        int result = reservationService.insert(reservation);
        log.info("예약 : " + reservation);
        if (result > 0) {
            return "redirect:/user/myPage/ptList";
        }
        return "redirect:/user/reservation/trainerReservation?error";
    }
    
    // 예약 취소(수정)
    @PostMapping("/trainerList")
    public String cancel(Reservation reservation) throws Exception {
        int result = reservationService.cancel(reservation);
        if (result > 0) {
            return "redirect:/user/myPage/ptList";
        }
        return "redirect:/user/reservation/trainerList?error";
    }
    
}