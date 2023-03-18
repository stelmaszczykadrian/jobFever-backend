package com.jobfever.model;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Candidate extends User{
    public Candidate(int id, String name, String password) {
        super(id, name, password);
    }
}
