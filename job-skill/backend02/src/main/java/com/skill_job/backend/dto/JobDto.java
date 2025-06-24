package com.skill_job.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private Integer job_Id;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private String salary;
}
