package com.gym.gym.service;

// import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gym.gym.domain.Comment;
import com.gym.gym.mapper.CommentMapper;

public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment selectByPlanNo(int planNo) throws Exception {
       Comment comment = commentMapper.selectByPlanNo(planNo);
       return comment;
    }

    @Override
    public Comment selectByNo(int no) throws Exception {
        Comment comment = commentMapper.selectByNo(no);
        return comment;
    }

    @Override
    public List<Comment> selectByTrainer(int trainerNo) throws Exception {
        List<Comment> commentList = commentMapper.selectByTrainer(trainerNo);
        return commentList;
    }
    
    @Override
    public List<Comment> selectByUser(int userNo) throws Exception {
        List<Comment> commentList = commentMapper.selectByUser(userNo);
        return commentList;
    }

    // @Override
    // public List<Comment> selectByUserDate(int userNo, Date startTime, Date endTime) throws Exception {
    //     List<Comment> commentList = commentMapper.selectByUserDate(userNo, startTime, endTime)
    // }

    @Override
    public int insert(Comment comment) throws Exception {
        int result = commentMapper.insert(comment);
        return result;
    }

    @Override
    public int update(int planNo) throws Exception {
        int result = commentMapper.update(planNo);
        return result;
    }
    
    @Override
    public int delete(int planNo) throws Exception {
        int result = commentMapper.delete(planNo);
        return result;       
    }
    
}
