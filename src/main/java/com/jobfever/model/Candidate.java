package com.jobfever.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidates")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String city;
    private String linkedin;
    private String github;
    @ToString.Exclude
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateEducation> candidateEducations = new ArrayList<>();
    @ToString.Exclude
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateExperience> candidateExperiences = new ArrayList<>();

    @Column(length = 500)
    private String imgFileName;

    @Column(length = 500)
    private String cvFile;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> rating;
}
