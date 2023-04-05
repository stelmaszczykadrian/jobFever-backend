package com.jobfever.model;

import com.jobfever.role.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String companyName;
    @NotEmpty
    private String nameAndSurname;

    @Min(value=9)
    private int phoneNumber;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany
    private List<Job> job;

    public Employer(String email, String password) {
        this.email = email;
        this.password = password;
        this.roleType = RoleType.EMPLOYER;
    }
}
