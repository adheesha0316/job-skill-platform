package com.skill_job.backend.repo;

import com.skill_job.backend.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepo extends JpaRepository<Employer, Integer> {
}
