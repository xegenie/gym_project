package com.gym.gym.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Users {
    
    private int no;
    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String birth;
    private String question;
    private String answer;
    private Date createdAt;
    private Date updatedAt;

}
