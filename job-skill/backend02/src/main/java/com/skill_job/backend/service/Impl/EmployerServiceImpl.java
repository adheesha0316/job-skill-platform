package com.skill_job.backend.service.Impl;

import com.skill_job.backend.dto.EmployerDto;
import com.skill_job.backend.dto.EmployerWithJobDto;
import com.skill_job.backend.dto.JobDto;
import com.skill_job.backend.entity.Employer;
import com.skill_job.backend.entity.Job;
import com.skill_job.backend.entity.User;
import com.skill_job.backend.repo.EmployerRepo;
import com.skill_job.backend.repo.UserRepo;
import com.skill_job.backend.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepo employerRepo;
    private final UserRepo userRepo;

    @Autowired
    public EmployerServiceImpl(EmployerRepo employerRepo, UserRepo userRepo) {
        this.employerRepo = employerRepo;
        this.userRepo = userRepo;
    }


    @Override
    public EmployerDto addEmployer(EmployerDto employerDto) {
        Optional<User> optionalUser = userRepo.findById(employerDto.getEmployer_id());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found for employer_id: " + employerDto.getEmployer_id());
        }

        User user = optionalUser.get();

        Employer employer = new Employer(user, employerDto.getCompanyName(), employerDto.getContactNumber(), employerDto.getAddress());
        Employer save = employerRepo.save(employer);

        return new EmployerDto(
                save.getEmployer_id(),
                save.getCompanyName(),
                save.getContactNumber(),
                save.getAddress()
        );
    }

    @Override
    public EmployerDto getEmployer(Integer employer_id) {
        return employerRepo.findById(employer_id)
                .map(emp -> new EmployerDto(
                        emp.getEmployer_id(),
                        emp.getCompanyName(),
                        emp.getContactNumber(),
                        emp.getAddress()
                ))
                .orElse(null);
    }

    @Override
    public EmployerDto updateEmployer(EmployerDto employerDto) {
        Optional<Employer> byId = employerRepo.findById(employerDto.getEmployer_id());
        if (byId.isPresent()) {
            Employer employer = byId.get();
            employer.setCompanyName(employerDto.getCompanyName());
            employer.setContactNumber(employerDto.getContactNumber());
            employer.setAddress(employerDto.getAddress());

            Employer save = employerRepo.save(employer);
            return new EmployerDto(
                    save.getEmployer_id(),
                    save.getCompanyName(),
                    save.getContactNumber(),
                    save.getAddress()
            );
        }
        return null;
    }

    @Override
    public EmployerDto deleteEmployer(Integer employer_id) {
        Optional<Employer> byId = employerRepo.findById(employer_id);
        if (byId.isPresent()) {
            employerRepo.deleteById(employer_id);
            Employer employer = byId.get();
            return new EmployerDto(employer.getEmployer_id(),
                    employer.getCompanyName(),
                    employer.getContactNumber(),
                    employer.getAddress());
        }
        return null;
    }

    @Override
    public List<EmployerDto> getAllEmployers() {
        List<Employer> employers = employerRepo.findAll();
        List<EmployerDto> employerDtos = new ArrayList<>();
        for (Employer employer : employers) {
            employerDtos.add(new EmployerDto(
                    employer.getEmployer_id(),
                    employer.getCompanyName(),
                    employer.getContactNumber(),
                    employer.getAddress()
            ));
        }
        return employerDtos;
    }

    @Override
    public EmployerWithJobDto saveEmployerWithJob(EmployerWithJobDto employerWithJobDto) {
        Optional<User> userOptional = userRepo.findById(employerWithJobDto.getEmployer_id());
        if (userOptional.isPresent()) {
            throw new RuntimeException("User not found for employer_id: " + employerWithJobDto.getEmployer_id());
        }

        User user = userOptional.get();
        Employer employer = new Employer(user, employerWithJobDto.getCompanyName(), employerWithJobDto.getContactNumber(), employerWithJobDto.getAddress());

        List<Job> jobList = new ArrayList<>();
        for (JobDto jobDtoDto : employerWithJobDto.getJobs()) {
            Job job = new Job();
            job.setTitle(jobDtoDto.getTitle());
            job.setDescription(jobDtoDto.getDescription());
            job.setLocation(jobDtoDto.getLocation());
            job.setJobType(jobDtoDto.getJobType());
            job.setSalary(jobDtoDto.getSalary());
            job.setEmployer(employer);
            jobList.add(job);
        }

        employer.setJobs(jobList);

        Employer saved = employerRepo.save(employer);

        List<JobDto> savedJobs = new ArrayList<>();
        for (Job job : saved.getJobs()) {
            savedJobs.add(new JobDto(
                    job.getJob_Id(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getLocation(),
                    job.getJobType(),
                    job.getSalary()
            ));
        }
        return new EmployerWithJobDto(
                saved.getEmployer_id(),
                saved.getCompanyName(),
                saved.getContactNumber(),
                saved.getAddress(),
                savedJobs
        );
    }

    @Override
    public List<EmployerWithJobDto> getEmployerWithJobs() {
        List<Employer> employers = employerRepo.findAll();
        List<EmployerWithJobDto> employerWithJobDtos = new ArrayList<>();

        for (Employer employer : employers) {
            List<JobDto> jobDtos = new ArrayList<>();
            for (Job job : employer.getJobs()) {
                jobDtos.add(new JobDto(
                        job.getJob_Id(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getLocation(),
                        job.getJobType(),
                        job.getSalary()
                ));
            }

            employerWithJobDtos.add(new EmployerWithJobDto(
                    employer.getEmployer_id(),
                    employer.getCompanyName(),
                    employer.getContactNumber(),
                    employer.getAddress(),
                    jobDtos
            ));
        }
        return employerWithJobDtos;
    }
}
