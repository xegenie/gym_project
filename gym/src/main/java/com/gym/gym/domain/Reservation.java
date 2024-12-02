package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reservation {
    
    private int no;
    private int userNo;
    private int trainerNo;
    private Date rvDate;
    private Date createdAt;
    private Date canceledAt;
    private int enabled;
    
}