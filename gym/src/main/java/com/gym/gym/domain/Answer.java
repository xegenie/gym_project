package com.gym.gym.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Answer {
    
    private int no;
    private int boardNo;
    private int userNo;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    
}
