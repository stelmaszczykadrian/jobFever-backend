package com.jobfever.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Job {
    private int id;
    private String title;
    private Employer employer;

}
