package com.gym.gym.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gym.gym.domain.Plan;

@Mapper
public interface PlanMapper {

    public List<Plan> listAll() throws Exception;

    public List<Plan> selectByUser(int userNo) throws Exception;

    public List<Plan> selectByUserDate(int userNo, Date startTime, Date endTime) throws Exception;

    public Plan selectByNo(int no) throws Exception;

    public int insert(Plan plan) throws Exception;

    public int update(Plan plan) throws Exception;

    public int delete(int no) throws Exception;
}
