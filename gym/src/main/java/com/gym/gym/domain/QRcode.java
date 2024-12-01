package com.gym.gym.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("QRcode")
public class QRcode {
    
    private int no;
    private int userNo;
    private String uuid;
    private Date createdAt;
    
}
