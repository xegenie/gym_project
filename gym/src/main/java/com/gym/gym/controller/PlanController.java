package com.gym.gym.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.gym.domain.Comment;
import com.gym.gym.domain.CustomUser;
import com.gym.gym.domain.Plan;
import com.gym.gym.domain.Reservation;
import com.gym.gym.service.CommentService;
import com.gym.gym.service.PlanService;
import com.gym.gym.service.ReservationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
// @EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequestMapping("/user/schedule")
public class PlanController {
    
    @Autowired
    PlanService planService;

    @Autowired
    CommentService commentService;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/plan")
    // @PreAuthorize("hasRole('USER')")
    public String list(@AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {
        Date currentDate = new Date();
        List<Date> dates = MonthFirstLast(currentDate);

        Date startDate = dates.get(0);
        Date endDate = dates.get(1);

        Long no = userDetails.getNo();
        int iNo = no.intValue();
        
        List<Plan> planList = planService.selectByStartEnd(iNo, startDate, endDate);
        List<Comment> commentList = commentService.selectByStartEnd(iNo, startDate, endDate);
        List<Reservation> reservationList = reservationService.selectByStartEnd(iNo, startDate, endDate);

        model.addAttribute("planList", planList);
        model.addAttribute("commentList", commentList);
        model.addAttribute("reservationList", reservationList);

        return "/user/plan/plan";
    }

    @ResponseBody
    @GetMapping("/plan/{year}/{month}/{day}")
    // @PreAuthorize("hasRole('USER')")
    public String listByDate(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day,
                             @AuthenticationPrincipal CustomUser userDetails, Model model) throws Exception {
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작하므로 -1 필요
        Date date = calendar.getTime();
        List<Date> dates = MonthFirstLast(date);

        Date startDate = dates.get(0);
        Date endDate = dates.get(1);

        Long no = userDetails.getNo();
        int iNo = no.intValue();
        
        List<Plan> planList = planService.selectByStartEnd(iNo, startDate, endDate);
        List<Comment> commentList = commentService.selectByStartEnd(iNo, startDate, endDate);
        List<Reservation> reservationList = reservationService.selectByStartEnd(iNo, startDate, endDate);

        model.addAttribute("planList", planList);
        model.addAttribute("commentList", commentList);
        model.addAttribute("reservationList", reservationList);

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
