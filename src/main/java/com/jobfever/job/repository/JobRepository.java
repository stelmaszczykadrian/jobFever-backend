package com.jobfever.job.repository;

import com.jobfever.employer.model.Employer;
import com.jobfever.job.model.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface JobRepository {
    public final static List<Job> jobList = new ArrayList<>(Arrays.asList(
            new Job(1, "Java Senior", new Employer(1,"Samsung")),
            new Job(2, "Java Junior",  new Employer(2, "Motorola")),
            new Job(3, "Java Medium", new Employer(3, "Sony"))
    ));
}
