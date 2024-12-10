package com.gym.gym.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.Page;

public interface BuyListService {
    
    public List<BuyList> list(@Param("keyword") String keyword, @Param("page") Page page) throws Exception;

    public int insert(BuyList buyList) throws Exception;

    public int cancel(int no) throws Exception;

    public List<BuyList> listByUser(Long no) throws Exception;

    public BuyList lastBuyList(Long no) throws Exception;

    public int count(@Param("keyword") String keyword) throws Exception;

    // 매출 내역
    public List<BuyList> salesList() throws Exception;

}
