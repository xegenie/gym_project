package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.Attendance;
import com.gym.gym.service.AttendanceService;

@Controller
@RequestMapping("")
public class RankingController {

    @Autowired
    private AttendanceService attendanceService;

    // 출석 랭킹

    @GetMapping("/ranking")
    public String attendanceRanking(Model model) {
        try {
            // 서비스에서 출석 랭킹 데이터를 조회
            List<Attendance> rankingList = attendanceService.getAttendanceRanking();

            // 모델에 출석 랭킹 데이터 추가
            model.addAttribute("rankingList", rankingList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "출석 랭킹 조회 중 오류가 발생했습니다.");
        }

        // 반환할 뷰 이름
        return "ranking"; // 출석 랭킹 뷰를 반환
    }

}
