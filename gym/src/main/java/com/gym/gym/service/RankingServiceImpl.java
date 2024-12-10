package com.gym.gym.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.Option;
import com.gym.gym.domain.Page;
import com.gym.gym.domain.Ranking;
import com.gym.gym.mapper.RankingMapper;

@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingMapper rankingMapper;

    @Override
    public List<Ranking> list() throws Exception {
        return rankingMapper.list(new Option(), new Page());
    }

    @Override
    public List<Ranking> list(String keyword) throws Exception {
        Option option = new Option();
        option.setKeyword(keyword);
        return rankingMapper.list(option, new Page());
    }

    @Override
    public List<Ranking> list(Option option) throws Exception {
        return rankingMapper.list(option, new Page());
    }

    @Override
    public List<Ranking> list(Option option, int rows) throws Exception {
        Page page = new Page();
        page.setRows(rows);
        return rankingMapper.list(option, page);
    }

    @Override
    public List<Ranking> list(Option option, Page page) throws Exception {
        int total = count(option);
        page.setTotal(total);
        return rankingMapper.list(option, page);
    }

    @Override
    public int count(Option option) throws Exception {
        return rankingMapper.count(option);
    }

    @Override
    public List<Ranking> getAttendanceRanking() throws Exception {
        return rankingMapper.getAttendanceRanking();
    }

    @Override
    public List<Ranking> getAttendanceRanking(Option option, Page page) throws Exception {
        List<Ranking> rankingList = rankingMapper.getAttendanceRanking(option, page);

        return rankingList.stream()
                .sorted(Comparator.comparingInt(Ranking::getAttendanceCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Ranking> getAttendanceRanking(Option option, Page page, int lastRank) throws Exception {
        // 데이터 조회
        List<Ranking> rankingList = rankingMapper.getAttendanceRanking(option, page);

        // 출석수 기준 내림차순 정렬
        List<Ranking> sortedList = rankingList.stream()
                .sorted(Comparator.comparingInt(Ranking::getAttendanceCount).reversed())
                .collect(Collectors.toList());

        // 기존 순위에 이어지는 순위 설정
        for (int i = 0; i < sortedList.size(); i++) {
            // lastRank를 기준으로 계속 이어지도록 순위 설정
            sortedList.get(i).setRank(lastRank + i + 1);
        }

        return sortedList;
    }
}