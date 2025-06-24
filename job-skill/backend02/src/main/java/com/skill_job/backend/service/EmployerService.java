package com.skill_job.backend.service;

import com.skill_job.backend.dto.EmployerDto;
import com.skill_job.backend.dto.EmployerWithJobDto;

import java.util.List;

public interface EmployerService {
    EmployerDto addEmployer(EmployerDto employerDto);
    EmployerDto getEmployer(Integer employer_id);
    EmployerDto updateEmployer(EmployerDto employerDto);
    EmployerDto deleteEmployer(Integer employer_id);
    List<EmployerDto> getAllEmployers();

    EmployerWithJobDto saveEmployerWithJob(EmployerWithJobDto employerWithJobDto);

    List<EmployerWithJobDto> getEmployerWithJobs();
}
