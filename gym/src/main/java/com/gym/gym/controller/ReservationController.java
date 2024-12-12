package com.gym.gym.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.Reservation;
import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.domain.Users;
import com.gym.gym.service.ReservationService;
import com.gym.gym.service.TrainerProfileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TrainerProfileService trainerProfileService;

    // 마이페이지 예약 목록 화면
    @GetMapping("/user/myPage/ptList")
    public String userReservationList(@AuthenticationPrincipal CustomUser userDetails, Model model, Option option,
            Page page) throws Exception {

        List<Reservation> reservationCount = reservationService.userByList(userDetails.getNo(), option, new Page());
        log.info("좀 찍혀라 " + reservationCount);
        long disabledCount = reservationService.disabledCount(userDetails.getNo());

        // 리스트의 마지막 항목
        if (!reservationCount.isEmpty()) {
            Reservation lastReservation = reservationCount.get(reservationCount.size() - 1);
            int ptCount = lastReservation.getPtCount();
            ptCount -= disabledCount;
            model.addAttribute("disabledCount", disabledCount);
            model.addAttribute("ptCount", ptCount);
            log.info("피티카운트 : " + ptCount);
            log.info("disabledCount: " + disabledCount);

        }

        List<Reservation> reservationList = reservationService.userByList(userDetails.getNo(), option, page);
        log.info("로그찍기 " + reservationList);

        model.addAttribute("reservationList", reservationList);

        model.addAttribute("option", option);
        model.addAttribute("rows", page.getRows());
        model.addAttribute("page", page);
        return "/user/myPage/ptList";
    }

    // 관리자 예약 목록 화면
    @GetMapping("/admin/reservation/list")
    public String adminReservationList(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model,
            @ModelAttribute Option option, @ModelAttribute Page page) throws Exception {

        List<Reservation> reservationList = reservationService.list(keyword, option, page);
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("option", option);
        model.addAttribute("rows", page.getRows());
        model.addAttribute("page", page);

        String pageUrl = UriComponentsBuilder.fromPath("/admin/reservation/list")
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

    public Date CalcOneHourLater(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.HOUR, 1);

        Date oneHourLater = calendar.getTime();

        return oneHourLater;
    }

    // 관리자 캘린더 화면
    @GetMapping("/admin/reservation/calendar")
    public String adminCalendar(Model model, @ModelAttribute Option option) throws Exception {

        List<Users> trainerUsers = reservationService.trainerUsers();
        List<Reservation> sortByTrainer = reservationService.sortByTrainer(option);

        List<Map<String, Object>> reservationEvents = new ArrayList<>();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        for (Reservation rv : sortByTrainer) {

            Map<String, Object> event = new HashMap<>();
            String formattedTime = timeFormat.format(rv.getRvDate());

            event.put("title", formattedTime + " " + rv.getUserName() + "님");
            event.put("start", rv.getRvDate());
            event.put("end", CalcOneHourLater(rv.getRvDate()));
            event.put("description", "");
            event.put("color", "lightblue");
            event.put("textColor", "#333");
            event.put("type", "reservation");
            event.put("user_no", rv.getUserNo());

            reservationEvents.add(event);
        }

        model.addAttribute("reservationEvents", reservationEvents);
        model.addAttribute("sortByTrainer", sortByTrainer);
        model.addAttribute("trainerUsers", trainerUsers);
        model.addAttribute("option", option);

        String pageUrl = UriComponentsBuilder.fromPath("/admin/reservation/calendar")
                .queryParam("keyword", option.getKeyword())
                .build()
                .toString();
        model.addAttribute("pageUrl", pageUrl);

        System.out.println("Selected keyword: " + option.getKeyword());
        return "/admin/reservation/calendar";
    }

    // 예약 등록 화면
    @GetMapping("/user/reservation/reservation")
    public String insert(@RequestParam("trainerNo") int trainerNo, @AuthenticationPrincipal CustomUser userDetails,
            Model model, @ModelAttribute Option option, Page page) throws Exception {

        List<Reservation> reservationCount = reservationService.userByList(userDetails.getNo(), option, new Page());
        // 트레이너 프로필 정보
        TrainerProfile trainerProfile = trainerProfileService.select(trainerNo);
        model.addAttribute("trainer", trainerProfile);

        log.info("넘어오나" + trainerProfile);
        List<Reservation> reservationList = reservationService.userByList(userDetails.getNo(), option, page);

        long disabledCount = reservationService.disabledCount(userDetails.getNo());
        // .filter(reservation -> reservation.getEnabled() == 2)
        // .count();

        // 리스트의 마지막 항목
        if (reservationCount.size() > 0) {
            Reservation lastReservation = reservationCount.get(reservationCount.size() - 1);
            int ptCount = lastReservation.getPtCount();
            ptCount -= disabledCount;
            model.addAttribute("ptCount", ptCount);
            log.info("피티카운트 : " + ptCount);
            log.info("disabledCount: " + disabledCount);
        }
        
        List<Reservation> sortByTrainer = reservationService.sortByTrainer(option)
                    .stream()
                    .filter(reservation -> reservation.getTrainerNo() == trainerNo)
                    .toList();

        model.addAttribute("reservationList", reservationList);
        model.addAttribute("sortByTrainer", sortByTrainer);

        return "user/reservation/reservation";
    }

    // 예약 등록 처리
    @PostMapping("/user/reservation/reservation")
    public String insertPro(Reservation reservation, @AuthenticationPrincipal CustomUser userDetails, Model model)
            throws Exception {
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
    public String cancelUser(@RequestParam("no") int no, Option option, Page page) throws Exception {
        Reservation reservation = reservationService.findByNo(no);

        reservation.setCanceledAt(new Date());
        reservation.setEnabled(0);
        int result = reservationService.cancel(reservation);

        if (result > 0) {
            return "redirect:/user/myPage/ptList?page=" + page.getPage() + "&keyword=" + option.getKeyword()
                    + "&orderCode=" + option.getOrderCode() + "&rows=" + page.getRows();
        }
        return "redirect:/user/myPage/ptList?error";
    }

    // 관리자가 예약 취소(수정)
    @PostMapping("/admin/reservation/list")
    public String cancelAdmin(RedirectAttributes redirectAttributes, @RequestParam("no") int no, Option option, Page page) throws Exception {
        Reservation reservation = reservationService.findByNo(no);

        reservation.setCanceledAt(new Date());
        reservation.setEnabled(0);
        int result = reservationService.cancel(reservation);

        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "예약이 취소되었습니다.");
            return "redirect:/admin/reservation/list?page=" + page.getPage() + "&keyword=" + option.getKeyword()
            + "&orderCode=" + option.getOrderCode() + "&rows=" + page.getRows();
        }
        return "redirect:/admin/reservation/list?error";
    }

}