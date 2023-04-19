package com.jobfever.model;

import com.jobfever.role.RoleType;
import com.jobfever.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "candidates")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotEmpty
//    @Email
//    private String email;
//    @NotEmpty
//    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.CANDIDATE;

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

}
