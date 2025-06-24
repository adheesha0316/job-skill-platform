package com.skill_job.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer job_Id;

    private String title;
    private String description;
    private String location;
    private String jobType;
    private String salary;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false) // FK to Employer
    private Employer employer;

}
