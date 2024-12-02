package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class File {
    
    private int no;
    private int profileNo;
    private String type;
    private String name;
    private String path;
    private String size;
    private Date createdAt;
    private Date updatedAt;
    
}
