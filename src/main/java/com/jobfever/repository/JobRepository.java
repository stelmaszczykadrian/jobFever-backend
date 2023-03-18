package com.jobfever.repository;

import com.jobfever.model.Employer;
import com.jobfever.model.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface JobRepository {
    public final static List<Job> jobList = new ArrayList<>(Arrays.asList(
            new Job(1, "Java Senior", new Employer(1,"Samsung", "password")),
            new Job(2, "Java Junior",  new Employer(2, "Motorola", "password")),
            new Job(3, "Java Medium", new Employer(3, "Sony", "password"))
    ));
}
