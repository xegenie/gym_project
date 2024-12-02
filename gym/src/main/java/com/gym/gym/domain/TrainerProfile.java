package com.gym.gym.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TrainerProfile {
    
    private int no;
    private int trainerNo;
    private String name;
    private String info;

    private List<MultipartFile> FileList;
}
