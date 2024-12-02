package com.gym.gym.service;

import java.util.List;

import com.gym.gym.domain.BuyList;

public interface BuyListService {

    public int insert(BuyList buyList) throws Exception;

    public int cancel(int no) throws Exception;
    
    public List<BuyList> list() throws Exception;

    public List<BuyList> listByUser() throws Exception;
}
