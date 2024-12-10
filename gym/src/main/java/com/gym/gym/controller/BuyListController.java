package com.gym.gym.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.service.BuyListService;
import com.gym.gym.service.TrainerProfileService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class BuyListController {

    @Autowired
    BuyListService buyListService;

    // 등록
    @GetMapping("/admin/sales/buyList/insert")
    public String insert() {
        return "/admin/sales/buyList/insert";
    }

    @PostMapping("/admin/sales/buyList/insert")
    public String insert(BuyList buyList) throws Exception {

        int result = buyListService.insert(buyList);

        if (result > 0) {
            return "redirect:/admin/sales/buyList";
        }
        return "admin/sales/buyList/insert/?error";
    }

    // 캔슬
    @PostMapping("/admin/sales/buyList/cancel")
    public String cancel(int no) throws Exception {
        int result = buyListService.cancel(no);

        if (result > 0) {
            return "redirect:/admin/sales/buyList";
        }

        return "admin/sales/buyList/cancel/?error";
    }

    // 전체리스트
    @GetMapping("/admin/sales/buyList")
    public String list(Model model) throws Exception {

        List<BuyList> buyList = buyListService.list();
        log.info("buyList : " + buyList);

        model.addAttribute("buyList", buyList);

        return "/admin/sales/buyList";
    }

    // 마이리스트
    @GetMapping("/user/myPage/buyList")
    public String listByUser(@AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {

        Long no = 0L;
        List<BuyList> buyList = new ArrayList<>();
        if (userDetails != null) {
            no = userDetails.getNo();
            buyList = buyListService.listByUser(no);
        }
        model.addAttribute("buyList", buyList);

        // 정상이면서 제일 오래된 이용권
        List<BuyList> filteredList = buyList.stream()
                .filter(b -> "정상".equals(b.getStatus()))
                .sorted(Comparator.comparing(BuyList::getStartDate)) // 날짜 순으로 정렬
                .collect(Collectors.toList());
        BuyList oldestBuyList = filteredList.isEmpty() ? null : filteredList.get(0);

        model.addAttribute("oldestBuyList", oldestBuyList);

        return "/user/myPage/buyList";
    }
}
