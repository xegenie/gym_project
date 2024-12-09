package com.gym.gym.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.UserAuth;
import com.gym.gym.domain.Users;
import com.gym.gym.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    // 마이페이지
    @GetMapping("/user/myPage/info")
    public String myPage(Model model, @AuthenticationPrincipal CustomUser authuser) throws Exception {
        Long no = authuser.getUser().getNo();
        Users user = userService.select(no);
        model.addAttribute("user", user);
        return "/user/myPage/info";
    }

    // 회원 수정 페이지 이동
    @GetMapping("/user/myPage/infoUpdate")
    public String getMethodName(Model model, @AuthenticationPrincipal CustomUser authuser) throws Exception {
        Long no = authuser.getUser().getNo();
        Users user = userService.select(no);
        model.addAttribute("user", user);
        return "/user/myPage/infoUpdate";
    }

    // 회원 정보 수정 처리
    @PostMapping("/user/myPage/infoUpdate")
    public String userupdate(Users user) throws Exception {
        int result = userService.update(user);

        if (result > 0) {

            return "redirect:info";
        }

        return "/";
    }

    // 회원 탈퇴
    @PostMapping("/user/myPage/delete")
    public String userdelete(@RequestParam("no") Long no, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int result = userService.deleteAuth(no);
        result = userService.delete(no);

        if (result > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            new SecurityContextLogoutHandler().logout(request, response, auth);

            return "redirect:/";
        }

        // 실패한 경우
        return "redirect:/user/mypage/list";
    }

    // 유저리스트
    @GetMapping("/admin/user/list")
    public String userlist(Model model
                        ,@ModelAttribute Option option, @ModelAttribute Page page) throws Exception {
        List<Users> userList = userService.list(option,page);

        System.out.println("Keyword: " + option.getKeyword());
    System.out.println("Order Code: " + option.getOrderCode());
    System.out.println("Code: " + option.getCode());

        model.addAttribute("userList", userList);
        model.addAttribute("option", option);
        model.addAttribute("rows", page.getRows());
        model.addAttribute("page", page);
        String pageUrl = UriComponentsBuilder.fromPath("admin/user/list")
                .queryParam("keyword", option.getKeyword())
                .queryParam("code", option.getCode())
                .queryParam("rows", page.getRows())
                .queryParam("orderCode", option.getOrderCode())
                .build()
                .toUriString();

        model.addAttribute("pageUrl", pageUrl);

        return "admin/user/list";
                        }

    // 관리자 : 회원 정보 수정 이동
    @GetMapping("/admin/user/update")
    public String adminUpdate(Model model, @RequestParam("no") Long no) throws Exception {
        Users user = userService.select(no);
        UserAuth userAuth = userService.selectAuth(no);
        model.addAttribute("userAuth", userAuth);
        model.addAttribute("user", user);
        return "/admin/user/update";
    }

    // 관리자 : 회원 정보 수정 처리
    @PostMapping("admin/user/update")
    public String adminupdate(Users user, @RequestParam("no") Long no, @RequestParam("auth") String auth)
            throws Exception {
        int result = userService.update(user);
        UserAuth userAuth = userService.selectAuth(no);
        userAuth.setAuth(auth);
        int result2 = userService.updateAuth(userAuth);

        if (result > 0) {

            return "redirect:list";
        }

        return "/";
    }

    // 관리자 : 회원 탈퇴
    @PostMapping("/admin/user/delete")
    public String postMethodName(@RequestParam("no") Long no, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int result = userService.deleteAuth(no);
        result = userService.delete(no);

        if (result > 0) {

            return "redirect:list";
        }

        // 실패한 경우
        return "redirect:/admin/user/update";
    }

    @GetMapping("user/findId")
    public String findId() {
        return "/user/findId";
    }

    @PostMapping("/user/findId")
    public String findId(Model model, @RequestParam("name") String name,
            @RequestParam("phone") String phone, @RequestParam("question") String question,
            @RequestParam("answer") String answer) throws Exception {

            log.info("이름" + name);
            log.info("번호" + phone);
            log.info("질문" + question);
            log.info("답변" + answer);

        // 이름, 전화번호, 질문, 답변을 기준으로 사용자 찾기
        Users foundUser = userService.findUserByDetails(name, phone, question, answer);

        if (foundUser != null && foundUser.getId() != null) {
            model.addAttribute("user", foundUser);
            model.addAttribute("no", 1);
            return "/user/find";
        } else {
            model.addAttribute("users", null);
            model.addAttribute("message", "사용자를 찾을 수 없습니다.");
            return "/user/find";
        }
    }

    @GetMapping("/user/find")
    public String getMethodName() {
        return "/user/find";
    }
    

    // 비밀번호 찾기페이지 이동
    @GetMapping("/user/findPassword")
    public String findPassword() {
        return "/user/findPassword";
    }

    // 비밀번호 찾기페이지 처리
    @PostMapping("/user/findPassword")
    public String findPassword(Model model, @RequestParam("name") String name,
            @RequestParam("phone") String phone, @RequestParam("question") String question,
            @RequestParam("answer") String answer, @RequestParam("id") String id) throws Exception {

        // 이름, 전화번호, 질문, 답변을 기준으로 사용자 찾기
        Users foundUser = userService.findUserByPassword(name, phone, question, answer, id);

        if (foundUser != null && foundUser.getId() != null) {
            String code = UUID.randomUUID().toString().substring(0, 6);
            model.addAttribute("code", code);
            model.addAttribute("no", foundUser.getNo());
            foundUser.setCode(code);
            userService.codeInsert(foundUser);
            return "/user/changePassword";
        } else {
            model.addAttribute("users", null);
            model.addAttribute("message", "입력하신 사용자를 찾을 수 없습니다.");
            return "/user/find";
        }

    }

    // 비밀번호 변경 페이지 이동
    @GetMapping("/user/changePassword")
    public String changePassword() {
        return "/user/changePassword";
    }

    // 비밀번호 변경 페이지 처리
    @PostMapping("/user/changePassword")
    public String changePassword(@RequestParam("code") String code,
            @RequestParam("password") String password, @RequestParam("no") Long no,
            RedirectAttributes redirectAttributes) throws Exception {
        Users user = userService.select(no);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedNewPassword = encoder.encode(password);
        user.setPassword(encodedNewPassword);
        int result = userService.passwordUpdate(user);
        if (result > 0) {
            user.setCode(null);
            userService.codeInsert(user);
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("error", "");
        user.setCode(null);
        userService.codeInsert(user);
        return "redirect:/login";
    }

    // 회원정보 수정 비밀번호 변경 페이지 이동
    @GetMapping("/user/myPage/changePw")
    public String changePw() {
        return "/user/myPage/changePw";
    }

    // 회원정보 수정 비밀번호 변경 처리
    @PostMapping("/user/myPage/changePw")
    public String changePw( @RequestParam("password") String password, 
                            @RequestParam("newPassword") String newPassword,  @AuthenticationPrincipal CustomUser authuser,RedirectAttributes redirectAttributes, Model model) throws Exception {
                                String code = UUID.randomUUID().toString().substring(0, 6);
        Users user = userService.select(authuser.getNo());
             user.setCode(code);
             userService.codeInsert(user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(password, user.getPassword())) {
            String encodedNewPassword = encoder.encode(newPassword);
            user.setPassword(encodedNewPassword);
            
          int result = userService.passwordUpdate(user);
            if (result > 0) {
                user.setCode(null);
                userService.codeInsert(user);
                return "redirect:info";
            }
        }
        redirectAttributes.addFlashAttribute("error", "");
        user.setCode(null);
        userService.codeInsert(user);
        return "/index";
    }

}
