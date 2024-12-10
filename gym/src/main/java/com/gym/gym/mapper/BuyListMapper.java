package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gym.gym.domain.BuyList;
import com.gym.gym.domain.Page;

@Mapper
public interface BuyListMapper {

    // public List<BuyList> list() throws Exception;
    public List<BuyList> list(@Param("keyword") String keyword, @Param("page") Page page) throws Exception;
    
    public int insert(BuyList buyList) throws Exception;
    
    public int cancel(int no) throws Exception;

    public List<BuyList> listByUser(Long no) throws Exception;

    public BuyList lastBuyList(Long no) throws Exception;

    public int statusUpdate() throws Exception;

}
