package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reservation {
    
    private int no;
    private Date rvDate;
    private int userNo;
    private int trainerNo;
    
}
