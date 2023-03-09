package com.jobfever.job.service;

import com.jobfever.job.model.Job;
import com.jobfever.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    public List<Job> getJobList() {
        return JobRepository.jobList.stream().toList();
    }
}
