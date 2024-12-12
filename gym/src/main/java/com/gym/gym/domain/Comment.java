package com.gym.gym.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Comment {

    private int no;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "DEFAULT")
    private Date commentDate;
    private int trainerNo;
    private int userNo;
    private String cContent;
    private String fContent;
    
}
