package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.Page;
import com.gym.gym.mapper.BuyListMapper;

@Service
public class BuyListServiceImpl implements BuyListService {

    @Autowired private BuyListMapper buyListMapper;

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
    public List<BuyList> list(String keyword, Page page) throws Exception {
        buyListMapper.statusUpdate();
        
        int total = count(keyword);
        page.setTotal(total);

        List<BuyList> buyList = buyListMapper.list(keyword, page);

        return buyList;
    }
    @Override
    public int count(String keyword) throws Exception {
        int total = buyListMapper.count(keyword);

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

    @Override
    public List<BuyList> salesList(Integer trainerName, Integer year, Integer month, Integer day) throws Exception {
        return buyListMapper.salesList(trainerName, year, month, day);
    }
    
}
