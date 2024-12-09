package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.gym.domain.Attendance;
import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.service.AttendanceService;

@Controller
@RequestMapping("/admin/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 출석 내역 조회 (페이징 기능 포함)
     * 
     * @param model
     * @param option
     * @param page
     * @return
     */
    @GetMapping("/list")
    public String list(Model model, Option option, Page page) {
        try {
            // 서비스에서 페이징 및 검색 조건을 기반으로 데이터를 조회
            List<Attendance> attendanceList = attendanceService.list(option, page);
            int result = attendanceService.listCount();
            // 모델에 데이터 및 옵션, 페이지 정보 추가
            model.addAttribute("attendanceList", attendanceList);
            model.addAttribute("option", option);
            model.addAttribute("rows", page.getRows());
            model.addAttribute("page", page);
            model.addAttribute("result", result);

            // 페이지 URL 생성 (옵션 및 페이지 정보를 포함)
            String pageUrl = UriComponentsBuilder.fromPath("/admin/attendance/list")
                    .queryParam("keyword", option.getKeyword())
                    .queryParam("code", option.getCode())
                    .queryParam("rows", page.getRows())
                    .queryParam("orderCode", option.getOrderCode())
                    .build()
                    .toUriString();

            model.addAttribute("pageUrl", pageUrl);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "출석 내역 조회 중 오류가 발생했습니다.");
        }

        // 반환할 뷰 이름
        return "admin/attendance/list";
    }

    // 출석 체크 페이지를 보여주는 GET 요청 처리
    @GetMapping("/check")
    public String showCheckPage(@RequestParam("qrcodeId") String qrcodeId, @RequestParam("uuid") String uuid, Model model) {
        model.addAttribute("qrcodeId", qrcodeId);
        model.addAttribute("uuid", uuid);
        return "check";
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
        return "redirect:/admin/attendance/list";
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
        return "admin/attendance/list";
    }



    
    // 출석 인원 수 

    @GetMapping("/")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    





}
