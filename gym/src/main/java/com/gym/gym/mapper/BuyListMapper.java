package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gym.gym.domain.BuyList;

@Mapper
public interface BuyListMapper {

    public int insert(BuyList buyList) throws Exception;

    public int cancel(int no) throws Exception;
    
    public List<BuyList> list() throws Exception;

    public List<BuyList> listByUser(Long no) throws Exception;

}
