package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.mapper.TrainerProfileMapper;

@Service
public class TrainerProfileServiceImpl implements TrainerProfileService {

    @Autowired private TrainerProfileMapper trainerProfileMapper;

    @Override
    public List<TrainerProfile> list() throws Exception {
        return trainerProfileMapper.list();
    }

    @Override
    public TrainerProfile select(int no) throws Exception {
        return trainerProfileMapper.select(no);
    }

    @Override
    public int insert(TrainerProfile trainerPofile) throws Exception {
        int result = trainerProfileMapper.insert(trainerPofile);
        return result;
    }

    @Override
    public int update(TrainerProfile trainerPofile) throws Exception {
        int result = trainerProfileMapper.update(trainerPofile);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = trainerProfileMapper.delete(no);
        return result;
    }
    
}
