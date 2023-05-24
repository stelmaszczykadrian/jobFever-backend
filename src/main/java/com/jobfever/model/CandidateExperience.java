package com.jobfever.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "candidate_experience")
@Data
@NoArgsConstructor
public class CandidateExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String position;
    private String companyName;
    private String location;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String industry;
    @Column(length = 2000)
    private String description;
    @ManyToOne
    @JsonIgnore
    private Candidate candidate;
}