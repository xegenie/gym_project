package com.gym.gym.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Users;
import com.gym.gym.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
public class HomeController {
    

    
    @Autowired
    private UserService userService;

    

    /**
     * 메인 화면
     * 🔗 [GET] - / 
     * 📄 index.html
     * @return
          * @throws Exception 
          */
         @GetMapping("")
         public String home(
            // Principal principal, Model model
        //    @AuthenticationPrincipal User authuser, Model model
              @AuthenticationPrincipal CustomUser authuser, Model model
             ) throws Exception {
        log.info(":::::::::: 메인 화면 ::::::::::");

        // if(principal != null){
        //     String username = principal.getName();
        //     Users user = userService.select(username);
        //     model.addAttribute("user", user);
        // }

        if(authuser != null){
        Users user = authuser.getUser();
        model.addAttribute("user", user);
     }
        
        return "index";
    }

 
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    
  
@GetMapping("/login")
public String login(@CookieValue(value = "remember-id", required = false) Cookie cookie
                                ,Model model
                                ,HttpServletRequest request) {
                //@CookieValue(value="쿠키 이름", required = 필수 여부)
                // required=true (default) : 쿠키를 필수로 가져와서 없으면 에러
                // required=false          : 쿠키 필수 X -> 쿠키가 없으면 null, 에러 발생

    String username = "";
    boolean rememberId = false;
    if(cookie != null){
        log.info("CookieName : " + cookie.getName() );
        log.info("CookieValue : " + cookie.getValue() );
        username = cookie.getValue();
        rememberId = true;
    }
    model.addAttribute("username", username);
    model.addAttribute("rememberId", rememberId);

    return "login";
}



    /**
     * 회원 가입 화면
     * 🔗 [GET] - /join
     * 📄 join.html
     * @return
     */
    @GetMapping("/join")
    public String join() {
        log.info(":::::::::: 회원 가입 화면 ::::::::::");
        return "join";
    }

    /**
     * 회원 가입 처리
     * 🔗 [POST] - /join
     * ➡   ⭕ /login
     *      ❌ /join?error
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/join")
    public String joinPro(Users user, HttpServletRequest request) throws Exception {
        log.info(":::::::::: 회원 가입 처리 ::::::::::");
        log.info("user : " + user);
        String plainPassword = user.getPassword();
        // 회원 가입 요청
      
        int result = userService.join(user);
        
        // 회원 가입 성공 시, 바로 로그인
        boolean loginResult = false;
        if( result > 0 ) {
            // 암호화 전 비밀번호 다시 세팅
            // 회원가입 시, 비밀번호 암호화하기 때문에, 
            user.setPassword(plainPassword);
            loginResult = userService.login(user, request);
            
        }
        if(loginResult){
        return "redirect:/";
     }
     if (result > 0) {
        return "redirect:/login";
     }
        return "redirect/join?error";
        
    }


    /**
     * 아이디 중복 검사
     * @param username
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/check/{id}")
    public ResponseEntity<Boolean> userCheck(@PathVariable("id") String id) throws Exception {
        log.info("아이디 중복 확인 : " + id);
        Users user = userService.selectId(id);
        // 아이디 중복
        if( user != null ) {
            log.info("중복된 아이디 입니다 - " + id);
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        // 사용 가능한 아이디입니다.
        log.info("사용 가능한 아이디 입니다." + id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // @GetMapping("error/403")
    // public String error403() {
    //     return "error/403";
    // }
    

    @GetMapping("user/findId")
    public String findId() {
        return "/user/findId";
    }

    @PostMapping("/user/findId")
    public String findId(Model model, @RequestParam("name") String name,
                                      @RequestParam("phone") String phone, @RequestParam("question") String question,
                                      @RequestParam("answer") String answer) throws Exception {
        
                                log.info(name + "이름");
                                log.info(phone + "전화번호");
                                log.info(question + "질문");
                                log.info(answer + "답변");
        // 이름, 전화번호, 질문, 답변을 기준으로 사용자 찾기
        Users foundUser = userService.findUserByDetails(name, phone, question, answer);
        
        log.info(foundUser + "아이디찾기");
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
    

    @GetMapping("/user/findPassword")
    public String findPassword() {
        return "/user/findPassword";
    }

    @PostMapping("/user/findPassword")
    public String findPassword(Model model, @RequestParam("name") String name,
                                      @RequestParam("phone") String phone, @RequestParam("question") String question,
                                      @RequestParam("answer") String answer, @RequestParam("id") String id) throws Exception {
        
                                log.info(name + "이름");
                                log.info(phone + "전화번호");
                                log.info(question + "질문");
                                log.info(answer + "답변");
        // 이름, 전화번호, 질문, 답변을 기준으로 사용자 찾기
        Users foundUser = userService.findUserByPassword(name, phone, question, answer, id);
        
        log.info(foundUser + "비밀번호찾기");
        if (foundUser != null && foundUser.getId() != null) {
            String code = UUID.randomUUID().toString().substring(0, 6) ;

            foundUser.setCode(code);
           model.addAttribute("code", code);
           model.addAttribute("no", foundUser.getNo());
            userService.codeInsert(foundUser);
            return "/user/changePassword";
        } else {
            model.addAttribute("users", null);
            model.addAttribute("message", "사용자를 찾을 수 없습니다.");
            return "/user/find";
        }

        
    }
    
@GetMapping("/user/changePassword")
public String changePassword() {
    return "/user/changePassword";
}

@PostMapping("/user/changePassword")
public String changePassword(@RequestParam("code") String code, @RequestParam("password") String password, 
                            @RequestParam("newPassword") String newPassword, @RequestParam("no") Long no) throws Exception {
    Users user = userService.select(no);
      
    if (user.getPassword() == password ) {
        user.setPassword(newPassword);
      int result = userService.passwordUpdate(user);
        if (result > 0) {
            user.setCode(null);
            userService.update(user);
            return "/login";
        }
        else{
            user.setCode(null);
            userService.update(user);
            return "/login";

        }

        
    }
    user.setCode(null);
    userService.update(user);
    return "/login";
}



    

}
