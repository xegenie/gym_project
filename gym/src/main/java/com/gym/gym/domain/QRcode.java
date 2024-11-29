package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class QRcode {
    
    private int no;
    private int userNo;
    private String uuid;
    private Date createdAt;
    
}
