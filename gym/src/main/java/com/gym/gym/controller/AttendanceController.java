package com.gym.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gym.gym.domain.Attendance;
import com.gym.gym.service.AttendanceService;

import java.util.List;

@Controller
@RequestMapping("/admin/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // 출석 내역 조회
    @GetMapping("/list")
    public String list(Model model) {
        try {
            List<Attendance> attendanceList = attendanceService.list();
            model.addAttribute("attendanceList", attendanceList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "출석 내역 조회 중 오류가 발생했습니다.");
        }
        return "admin/attendance/list"; // 경로 수정 (슬래시 제거)
    }

    // 출석 체크 (등록)
    @PostMapping("/check")
    public String insertAttendance(@ModelAttribute Attendance attendance, Model model) {
        try {
            attendanceService.insertAttendance(attendance);
            model.addAttribute("successMessage", "출석 체크가 완료되었습니다.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "출석 체크 중 오류가 발생했습니다.");
        }
        return "redirect:attendance/list"; // 리디렉션 경로 수정 (슬래시 제거)
    }

    // 유저 출석 내역 검색
    @GetMapping("/search")
public String searchAttendance(@RequestParam("searchKeyword") String searchKeyword, Model model) {
    try {
        List<Attendance> attendanceList = attendanceService.searchAttendance(searchKeyword);
        model.addAttribute("attendanceList", attendanceList);
    } catch (Exception e) {
        model.addAttribute("errorMessage", "출석 내역 조회 중 오류가 발생했습니다.");
    }
    return "admin/attendance/list"; // 수정된 부분: "/admin/attendance/list" 대신 "admin/attendance/list"로 변경
}


    // 출석 랭킹 조회
    @GetMapping("/ranking")
    public String getAttendanceRanking(Model model) {
        try {
            List<Attendance> attendanceRanking = attendanceService.getAttendanceRanking();
            model.addAttribute("attendanceRanking", attendanceRanking);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "출석 랭킹 조회 중 오류가 발생했습니다.");
        }
        return "attendance/ranking";
    }
}

