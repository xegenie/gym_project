package com.gym.gym.service;

import java.util.Date;
import java.util.List;

import com.gym.gym.domain.Comment;

public interface CommentService {
    public Comment selectByPlanNo(int planNo)throws Exception;

    public Comment selectByNo(int no)throws Exception;

    public List<Comment> selectByTrainer(int trainerNo) throws Exception;

    public List<Comment> selectByUser(int userNo) throws Exception;
    
    // public List<Comment> selectByUserDate(int userNo, Date startTime, Date endTime) throws Exception;

    public int insert(Comment comment) throws Exception;

    public int update(int planNo) throws Exception;

    public int delete(int planNo) throws Exception;
}
