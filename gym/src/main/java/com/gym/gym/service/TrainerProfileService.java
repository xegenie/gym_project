package com.gym.gym.service;

import java.util.List;

import com.gym.gym.domain.TrainerProfile;

public interface TrainerProfileService {

    public List<TrainerProfile> list() throws Exception;
    
    public TrainerProfile select(int no) throws Exception;

    public int insert(TrainerProfile trainerPofile) throws Exception;

    public int update(TrainerProfile trainerPofile) throws Exception;
    
    public int delete(int no) throws Exception;
    
}
