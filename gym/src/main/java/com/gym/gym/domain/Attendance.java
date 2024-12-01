package com.gym.gym.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Attendance")
public class Attendance {
    
    private String qrId;
    private int userNo;
    private Date checkTime;
    
}
