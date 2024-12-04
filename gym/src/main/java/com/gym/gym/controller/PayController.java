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
import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.domain.TrainerWithCount;
import com.gym.gym.service.BuyListService;
import com.gym.gym.service.TrainerProfileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class PayController {

    @Autowired
    BuyListService buyListService;
    @Autowired
    TrainerProfileService trainerProfileService;

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
    public String normal() {
        return "/user/ticket/normal";
    }

    // 트레이너 목록
    @GetMapping("/ticket/trainerList")
    public String trainerList(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword)
            throws Exception {
        List<TrainerProfile> trainerList = trainerProfileService.list(keyword);
        List<TrainerProfile> userCount = trainerProfileService.userCount();

        List<TrainerWithCount> trainerWithCounts = new ArrayList<>();

        for (TrainerProfile trainer : trainerList) {
            // 트레이너의 trainerNo에 맞는 userCount를 찾아서 매핑
            boolean found = false; // 매칭된 항목이 있는지 확인
            for (TrainerProfile userCountItem : userCount) {
                if (trainer.getTrainerNo() == userCountItem.getTrainerNo()) {
                    trainerWithCounts.add(new TrainerWithCount(trainer, userCountItem.getCount()));
                    found = true;
                    break;
                }
            }

            // 매칭되지 않으면 count는 0
            if (!found) {
                trainerWithCounts.add(new TrainerWithCount(trainer, 0));
            }
        }

        model.addAttribute("trainerWithCounts", trainerWithCounts);
        return "/user/ticket/trainerList";
    }

    // 트레이너 상세
    @GetMapping("/ticket/trainerDetail")
    public String trainerDetail(@RequestParam("no") int trainerNo, Model model) throws Exception {
        TrainerProfile trainerProfile = trainerProfileService.select(trainerNo);

        model.addAttribute("trainer", trainerProfile);

        return "/user/ticket/trainerDetail";
    }

    @GetMapping("/ticket/table")
    public String table() {
        return "/user/ticket/table";
    }

}
