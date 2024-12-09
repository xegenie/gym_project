package com.gym.gym.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Ticket;
import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.domain.Users;
import com.gym.gym.service.BuyListService;
import com.gym.gym.service.TicketService;
import com.gym.gym.service.TrainerProfileService;
import com.gym.gym.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class PayController {

    @Autowired
    private BuyListService buyListService;
    @Autowired
    private TrainerProfileService trainerProfileService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    
    // 이용권 선택
    @GetMapping("/ticket/choice")
    public String choice(@AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {

        Long no = 0L;
        List<BuyList> buyList = new ArrayList<>();
        if (userDetails != null) {
            no = userDetails.getNo();
            buyList = buyListService.listByUser(no);
        }

        model.addAttribute("buyList", buyList);

        return "/user/ticket/choice";
    }

    // 일반 이용권
    @GetMapping("/ticket/normal")
    public String normal(@AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {
        // 티켓 조회
        List<Ticket> ticketList = ticketService.normalList();
        model.addAttribute("ticketList", ticketList);

        // 유저 조회
        if (userDetails != null) {
            Users user = userService.select(userDetails.getNo());
            model.addAttribute("user", user);
        }
        return "/user/ticket/normal";
    }

    // 트레이너 목록
    @GetMapping("/ticket/trainerList")
    public String trainerList(Model model)
            throws Exception {

        List<TrainerProfile> trainerList = trainerProfileService.list();

        model.addAttribute("trainerList", trainerList);

        return "/user/ticket/trainerList";
    }

    // 트레이너 상세
    @GetMapping("/ticket/trainerDetail")
    public String trainerDetail(@RequestParam("trainerNo") int trainerNo, Model model) throws Exception {
        TrainerProfile trainerProfile = trainerProfileService.select(trainerNo);

        model.addAttribute("trainer", trainerProfile);

        // 티켓 조회
        List<Ticket> ticketList = ticketService.ptList();
        model.addAttribute("ticketList", ticketList);

        return "/user/ticket/trainerDetail";
    }

    // 결제 페이지
    @GetMapping("/pay/payResult")
    public String payResult() {
        return "/user/pay/payResult";
    }
    

}
