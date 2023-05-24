package com.jobfever.model;

import com.jobfever.role.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Admin")
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
        this.roleType = RoleType.ADMIN;
    }
}
