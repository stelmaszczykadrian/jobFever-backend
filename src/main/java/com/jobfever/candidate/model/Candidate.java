package com.jobfever.candidate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Candidate {

    private int candidateId;
    private String name;
    private String password;
}
