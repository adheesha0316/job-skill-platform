package com.skill_job.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerWithJobDto {
    private Integer employer_id;
    private String companyName;
    private String contactNumber;
    private String address;
    private List<JobDto> jobs;
}
