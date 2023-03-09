package com.jobfever.job.model;

import com.jobfever.employer.model.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Job {

    private int jobId;
    private String title;

    private Employer employer;

}
