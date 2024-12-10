package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.Page;
import com.gym.gym.mapper.BuyListMapper;
import com.gym.gym.mapper.TrainerProfileMapper;

@Service
public class BuyListServiceImpl implements BuyListService {

    @Autowired private BuyListMapper buyListMapper;
    @Autowired private TrainerProfileMapper trainerProfileMapper;

    @Override
    public int insert(BuyList buyList) throws Exception {
        int result = buyListMapper.insert(buyList);
        return result;
    }

    @Override
    public int cancel(int no) throws Exception {
        int result = buyListMapper.cancel(no);
        return result;
    }

    @Override
    public List<BuyList> list() throws Exception {
        return buyListMapper.list("", new Page());
    }
   @Override
    public List<BuyList> list(String keyword, Page page) throws Exception {
        
        int total = count(keyword);
        page.setTotal(total);

        return buyListMapper.list(keyword, page);
    }
    @Override
    public int count(String keyword) throws Exception {
        int total = trainerProfileMapper.count(keyword);

        return total;
    }

    @Override
    public List<BuyList> listByUser(Long no) throws Exception {
        return buyListMapper.listByUser(no);
    }

    @Override
    public BuyList lastBuyList(Long no) throws Exception {
        return buyListMapper.lastBuyList(no);
    }
    
}
