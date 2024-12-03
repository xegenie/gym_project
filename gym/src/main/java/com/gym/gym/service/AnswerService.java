package com.gym.gym.service;

import java.util.List;

import com.gym.gym.domain.Answer;

public interface AnswerService {

    
    
public List<Answer> list() throws Exception;

public Answer select(int no) throws Exception;

    public int insert(Answer answer) throws Exception;

    public int update(Answer answer) throws Exception;
    
    public int delete(int no) throws Exception;

    public List<Answer> listByParent(int boardNo) throws Exception;

    public int deleteByParent(int boardNo ) throws Exception;


}
