package com.gym.gym.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.Comment;
import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Plan;
import com.gym.gym.service.CommentService;
import com.gym.gym.service.PlanService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
@RequestMapping("/user/schedule")
public class PlanController {
    
    @Autowired
    PlanService planService;

    @Autowired
    CommentService commentService;

    @GetMapping("/plan")
    public String list(@AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {
        Date currentDate = new Date();
        List<Date> dates = MonthFirstLast(currentDate);

        Date startDate = dates.get(0);
        Date endDate = dates.get(1);

        Long no = userDetails.getNo();
        int iNo = no.intValue();
        
        List<Plan> planList = planService.selectByStartEnd(iNo, startDate, endDate);
        List<Comment> commentList = commentService.selectByStartEnd(iNo, startDate, endDate);

        model.addAttribute("planList", planList);
        model.addAttribute("commentList", commentList);

        return "/user/plan/plan";
    }

    @PostMapping("/insert")
    public String insert(Plan plan) throws Exception {
        int result = planService.insert(plan);
        if (result>0) {
            return "redirect:/user/schedule/plan";
        } 
        return "redirect:/user/schedule/insert?error";
    }
    
    public List<Date> MonthFirstLast(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        LocalDate calendarStartDate = firstDayOfMonth.minusDays(firstDayOfWeek % 7);

        LocalDate calendarEndDate = lastDayOfMonth.plusDays(42 - (firstDayOfWeek + lastDayOfMonth.getDayOfMonth() - 1) % 7 - 1);

        Date startDate = Date.from(calendarStartDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()); // 00:00:00
        Date endDate = Date.from(calendarEndDate.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant()); 

        List<Date> dates = new ArrayList<Date>();
        dates.add(startDate);
        dates.add(endDate);

        return dates;
    }

}
