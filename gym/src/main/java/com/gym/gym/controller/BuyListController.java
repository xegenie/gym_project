package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.CustomUser;
import com.gym.gym.service.BuyListService;

import lombok.extern.slf4j.Slf4j;




@Controller
@Slf4j
public class BuyListController {
    
    @Autowired BuyListService buyListService;

    // 등록
    @GetMapping("/admin/sales/buyList/insert")
    public String insert() {
        return "/admin/sales/buyList/insert";
    }
    

    @PostMapping("/admin/sales/buyList/insert")
    public String insert(BuyList buyList) throws Exception {

        int result = buyListService.insert(buyList);
        
        if ( result > 0 ) {
            return "redirect:/admin/sales/buyList";
        }
        return "admin/sales/buyList/insert/?error";
    }
    
    
    // 캔슬
    @PostMapping("/admin/sales/buyList/cancel")
    public String cancel(int no) throws Exception {
        int result = buyListService.cancel(no);
        
        if ( result > 0 ) {
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
        
        Long no = userDetails.getNo();

        List<BuyList> buyList = buyListService.listByUser(no);

        model.addAttribute("buyList", buyList);

        return "/user/myPage/buyList";
    }

}
