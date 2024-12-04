package com.gym.gym.service;

import java.util.List;

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
    public List<Ranking> getAttendanceRanking(Option option, Page page) throws Exception {
        return rankingMapper.getAttendanceRanking(option, page);
    }

    @Override
    public List<Ranking> getAttendanceRanking() throws Exception {
        return rankingMapper.getAttendanceRanking();
    }
}
