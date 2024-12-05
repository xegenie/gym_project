package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.Reservation;
import com.gym.gym.service.ReservationService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 트레이너 리스트  
    // @GetMapping("/user/trainerList")
    // public String trainerList() {
    //     return "/user/reservation/trainerList";
    // }
    
    // 마이페이지 예약 목록 화면
    @GetMapping("/user/myPage/ptList")
    public String userReservationList(Model model, Option option, Page page) throws Exception {
        List<Reservation> reservationList = reservationService.list(option, page);
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("option", option);
        model.addAttribute("rows", page.getRows());
        model.addAttribute("page", page);
        return "/user/myPage/ptList";
    }
    
    // 관리자 예약 목록 화면
    @GetMapping("/admin/reservation/list")
    public String adminReservationList(Model model, Option option, Page page) throws Exception {


        List<Reservation> reservationList = reservationService.list(option, page);
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("option", option);
        model.addAttribute("rows", page.getRows());
        model.addAttribute("page", page);

        String pageUrl =  UriComponentsBuilder.fromPath("/admin/reservation/list")
                            // .queryParam("page", page.getPage())
                            .queryParam("keyword", option.getKeyword())
                            .queryParam("code", option.getCode())
                            .queryParam("rows", page.getRows())
                            .queryParam("orderCode", option.getOrderCode())
                            .build()
                            .toString();
        model.addAttribute("pageUrl", pageUrl);
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
    public String insertPro(Reservation reservation, @AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {
        log.info("예약 되나? : " + reservation);

        Long no = userDetails.getNo();
        
        reservation.setUserNo(no);
         
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
    public String cancelAdmin(Reservation reservation, Page page) throws Exception {
        int result = reservationService.cancel(reservation);
        log.info(page.getPage()+"페이지");
        if (result > 0) {
            return "redirect:/admin/reservation/list?page=" + page.getPage();
        }
        return "redirect:/admin/reservation/list?error";
    }
    
    
}