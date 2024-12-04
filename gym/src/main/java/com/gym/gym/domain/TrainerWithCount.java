package com.gym.gym.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerWithCount {
    private TrainerProfile trainer;
    private int count;
}
