package com.gym.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Users;
import com.gym.gym.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
@RequestMapping("/user/myPage")
public class UserController {
    
    @Autowired
    private UserService userService;




    // 마이페이지
@GetMapping("/info")
public String myPage(Model model, @AuthenticationPrincipal CustomUser authuser) throws Exception {
    Long no = authuser.getUser().getNo();
    Users user = userService.select(no);
    model.addAttribute("user", user);
    return "/user/myPage/info";
}

// 회원 수정 페이지 이동
@GetMapping("/infoUpdate")
public String getMethodName(Model model, @AuthenticationPrincipal CustomUser authuser) throws Exception {
    Long no = authuser.getUser().getNo();
    Users user = userService.select(no);
    model.addAttribute("user", user);
    return "/user/myPage/infoUpdate";
}

// 회원 정보 수정 처리
@PostMapping("/infoUpdate")
public String postMethodName(Users user) throws Exception {
    int result = userService.update(user);
    
    if (result > 0) {

        return "redirect:info";
    }

    return "/";
}



}
