package com.jobfever.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class User {
    private int id;
    private String name;
    private String password;
}
