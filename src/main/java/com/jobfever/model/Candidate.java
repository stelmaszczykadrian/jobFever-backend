package com.jobfever.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfever.role.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.CANDIDATE;

    private String name;
    private String city;
    private String linkedin;
    private String github;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "candidate")
    private List<CandidateEducation> candidateEducations;
    @OneToMany
    private List<CandidateExperience> candidateExperiences;

    public Candidate(String email, String password) {
        this.email = email;
        this.password = password;
        this.roleType = RoleType.CANDIDATE;
    }
}
