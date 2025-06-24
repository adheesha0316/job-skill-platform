package com.skill_job.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private Integer employer_id;
    private String companyName;
    private String contactNumber;
    private String address;

    private List<JobDto> jobs;

    public EmployerDto(Integer employerId, String companyName, String contactNumber, String address) {
        this.employer_id = employerId;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.address = address;
    }
}
