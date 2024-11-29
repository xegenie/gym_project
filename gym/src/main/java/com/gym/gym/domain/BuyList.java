package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BuyList {
    
    private int no;
    private int trainerNo;
    private int userNo;
    private int pNo;
    private Date buyDate;

}
