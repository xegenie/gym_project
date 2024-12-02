package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Plan {
    
    private int no;
    private int userNo;
    private int rvNo;
    private String planName;
    private Date planTime;
    private String planContent;
}
