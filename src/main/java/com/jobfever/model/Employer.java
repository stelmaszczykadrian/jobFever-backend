package com.jobfever.model;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Employer extends User{
    public Employer(int id, String name, String password) {
        super(id, name, password);
    }
}
