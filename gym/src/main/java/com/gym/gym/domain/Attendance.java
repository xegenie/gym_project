package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Attendance {
    
    private String qrId;
    private int userNo;
    private Date checkTime;
    
}
