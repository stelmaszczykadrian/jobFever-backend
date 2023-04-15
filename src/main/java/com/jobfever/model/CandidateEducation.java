package com.jobfever.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidate_education")
@Data
@NoArgsConstructor
public class CandidateEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(length = 2000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}

