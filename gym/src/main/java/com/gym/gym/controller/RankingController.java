package com.gym.gym.controller;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

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
            @ModelAttribute Page page,
            @RequestParam(value = "page", defaultValue = "1") int currentPage) {
        
        // 현재 페이지 번호 설정
        page.setPage(currentPage);

        List<Ranking> rankingList = null;
        try {
            // 기본값 설정
            if (page.getRows() <= 0) {
                page.setRows(10);
            }
            page.setIndex((page.getPage() - 1) * page.getRows());


            // 출석 랭킹 서비스 호출
            rankingList = rankingService.getAttendanceRanking(option, page);
            int total = rankingService.count(option); // 총 레코드 수 계산
            page.setTotal(total);

            // Model 속성 설정
            model.addAttribute("rankingList", rankingList);
            model.addAttribute("option", option);
            model.addAttribute("rows", page.getRows());
            model.addAttribute("page", page);
            model.addAttribute("total", total);

            // 페이지 URL 생성 (중복되는 'page' 파라미터 제거)
            String pageUrl = UriComponentsBuilder.fromPath("/ranking")
                    .queryParam("keyword", option.getKeyword())
                    .queryParam("code", option.getCode())
                    .queryParam("rows", page.getRows())
                    .queryParam("orderCode", option.getOrderCode())
                    .build()
                    .toUriString();

            model.addAttribute("pageUrl", pageUrl);

        } catch (Exception e) {
            log.error("출석 랭킹 조회 중 오류 발생", e);
            model.addAttribute("errorMessage", "출석 랭킹 조회 중 오류가 발생했습니다.");
            rankingList = Collections.emptyList(); // 오류 시 빈 리스트
            model.addAttribute("rankingList", rankingList);
        }

        return "ranking";
    }
}
