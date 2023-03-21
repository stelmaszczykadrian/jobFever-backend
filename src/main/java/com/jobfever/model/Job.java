package com.jobfever.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String title;
    private String description;
    private String technicalRequirements;
    private String responsibilities;
    private String whoWeAreLookingFor;
    private String benefits;
    private String city;
    private String address;
    private float salary;
    private String currency;
    private LocalDateTime postingDate;

//    private Employer employer;

}
