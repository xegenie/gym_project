package com.gym.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.service.PlanService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/user/schedule")
public class PlanController {
    
    @Autowired
    PlanService planService;

    @GetMapping("/plan")
    public String getMethodName() {

        
        return "/user/plan/plan";
    }
    

}
