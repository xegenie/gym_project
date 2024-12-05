package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

    private int no;
    private Date commentDate;
    private int trainerNo;
    private int userNo;
    private String cContent;
    private String fContent;
    
}
