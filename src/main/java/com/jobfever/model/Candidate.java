package com.jobfever.model;

import com.jobfever.role.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.CANDIDATE;

    public Candidate(String email, String password) {
        this.email = email;
        this.password = password;
        this.roleType = RoleType.CANDIDATE;
    }
}
