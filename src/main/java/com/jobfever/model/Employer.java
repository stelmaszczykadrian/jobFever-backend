package com.jobfever.model;

import com.jobfever.model.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String nameAndSurname;
    private String linkedin;
    @Min(value = 9)
    private int phoneNumber;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Column(length = 2000)
    private String aboutUs;
    @Column(length = 2000)
    private String whyWorthWorkingWithUs;
    @Column(length = 500)
    private String localization;
    @Column(length = 500)
    private String imgFileName;
    @Column(length = 10)
    private long nip;
    @OneToMany
    private List<Job> job;
}
