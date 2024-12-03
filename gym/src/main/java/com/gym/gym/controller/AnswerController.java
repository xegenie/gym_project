package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.gym.domain.Answer;
import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Users;
import com.gym.gym.service.AnswerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@Controller
@Slf4j
@RequestMapping("/answer")

public class AnswerController {
    
    @Autowired
    private AnswerService answerService;

    @ResponseBody
    @PostMapping("")
    public String answerInsert( @AuthenticationPrincipal CustomUser authuser,@RequestBody Answer answer) throws Exception {
        Users user = authuser.getUser();
        answer.setUserNo(user.getNo());
        int result = answerService.insert(answer);
        if (result > 0) {
            return "SUCCESS";
        }
        return "FAIL";
    }
    

    @GetMapping
    public String answerList(Model model, @RequestParam("boardNo") Long boardNo) throws Exception {
        List<Answer> answerList = answerService.listByParent(boardNo);
        log.info("이름왜 안넘어옴?" + answerList);
        model.addAttribute("answerList", answerList);
        return "user/answer/list";
    }
    

    @ResponseBody
    @DeleteMapping("/{no}")
    public String deleteAnswer(  @PathVariable("no") Long no) throws Exception{
      
        int result = answerService.delete(no);

        if(result > 0){
            return "SUCCESS";
        }
        return "FAIL";
    }


    @GetMapping("/{no}")
    public String editAnswer(Answer answer) throws Exception {
        int result = answerService.update(answer);
        if(result > 0){
            return "SUCCESS";
        }
        return "FAIL";
    }

    @ResponseBody
    @PutMapping("")
    public String updateAnswer(@RequestBody Answer answer) throws Exception {
        int result = answerService.update(answer);
        log.info("결과나옴?" + result + answer);
        if(result > 0){
            return "SUCCESS";
        }
        return "FAIL";

    }




}
