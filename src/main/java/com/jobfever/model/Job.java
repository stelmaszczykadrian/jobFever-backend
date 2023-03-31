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
    @Column(length = 2000)
    private String description;
    private String technicalRequirements;
    @Column(length = 2000)
    private String responsibilities;
    @Column(length = 2000)
    private String whoWeAreLookingFor;
    @Column(length = 2000)
    private String benefits;
    private String location;
    private float salaryFrom;
    private float salaryTo;
    private String jobType;
    private String currencyType;
    private String workOptions;
    private LocalDateTime postingDate = LocalDateTime.now();
    //many to one
    @ManyToOne
    private Employer employer;

}
