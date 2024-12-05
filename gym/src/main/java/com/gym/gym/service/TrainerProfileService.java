package com.gym.gym.service;

import java.util.List;
import java.util.Map;

import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.domain.Users;

public interface TrainerProfileService {

    public List<TrainerProfile> list() throws Exception;
    public List<TrainerProfile> list(String keyword) throws Exception;
    
    public TrainerProfile select(int no) throws Exception;

    public int insert(TrainerProfile trainerPofile) throws Exception;

    public int update(TrainerProfile trainerPofile) throws Exception;
    
    public int delete(int no) throws Exception;
    
    public List<Users> trainerUsers() throws Exception;
}
