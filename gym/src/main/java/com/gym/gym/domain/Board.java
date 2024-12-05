package com.gym.gym.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
    
    private Long no;
    private Long userNo;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private String auth;
    private String name;
    
}
