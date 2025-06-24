package com.skill_job.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    private Integer employer_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "employer_id")
    private User user;

    private String companyName;
    private String contactNumber;
    private String address;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;

    public Employer(User user, String companyName, String contactNumber, String address) {
        this.user = user;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.address = address;
    }


}
