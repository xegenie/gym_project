package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gym.gym.domain.Answer;

@Mapper
public interface AnswerMapper {

public List<Answer> list() throws Exception;

public Answer select(int no) throws Exception;

    public int insert(Answer answer) throws Exception;

    public int update(Answer answer) throws Exception;
    
    public int delete(int no) throws Exception;

    public List<Answer> listByParent(int boardNo) throws Exception;

    public int deleteByParent(int boardNo ) throws Exception;



}
