package com.gym.gym.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.Ranking;
import com.gym.gym.service.RankingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/ranking")
    public String attendanceRanking(
            Model model,
            @ModelAttribute Option option,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            @RequestParam(value = "page", defaultValue = "1") int currentPage) {

        try {
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                option.setKeyword(searchKeyword);
            }

            Page page = new Page();
            page.setPage(currentPage);
            page.setRows(10);
            page.setIndex((currentPage - 1) * page.getRows());

            List<Ranking> rankingList = rankingService.getAttendanceRanking(option, page);
            int total = rankingService.count(option);

            page.setTotal(total);
            model.addAttribute("rankingList", rankingList);
            model.addAttribute("option", option);
            model.addAttribute("page", page);
            model.addAttribute("total", total);

        } catch (Exception e) {
            log.error("출석 랭킹 조회 중 오류 발생", e);
            model.addAttribute("errorMessage", "출석 랭킹 조회 중 오류가 발생했습니다.");
            model.addAttribute("rankingList", Collections.emptyList());
        }

        return "ranking";
    }
}
