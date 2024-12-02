package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gym.gym.domain.TrainerProfile;

@Mapper
public interface TrainerProfileMapper {
    
    // public List<TrainerProfile> list() throws Exception;
    public List<TrainerProfile> list(String keyword) throws Exception;
    
    public TrainerProfile select(int no) throws Exception;

    public int insert(TrainerProfile trainerPofile) throws Exception;

    public int update(TrainerProfile trainerPofile) throws Exception;
    
    public int delete(int no) throws Exception;
    
}
