package com.jobfever.model;

import com.jobfever.role.Role;
import com.jobfever.role.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Employer(String email, String password) {
        this.email = email;
        this.password = password;
        this.roleType = RoleType.EMPLOYER;
    }
}
