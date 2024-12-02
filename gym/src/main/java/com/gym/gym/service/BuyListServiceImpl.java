package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.BuyList;
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
    public List<BuyList> list() throws Exception {
        return buyListMapper.list();
    }

    @Override
    public List<BuyList> listByUser(Long no) throws Exception {
        return buyListMapper.listByUser(no);
    }
    
}
