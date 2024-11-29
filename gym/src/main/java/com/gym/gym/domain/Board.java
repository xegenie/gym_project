package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
    
    private int no;
    private int userNo;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    
}
