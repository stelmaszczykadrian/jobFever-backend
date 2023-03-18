package com.jobfever.service;

import com.jobfever.model.Job;
import com.jobfever.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    public List<Job> getJobList() {
        return JobRepository.jobList.stream().toList();
    }

    public String addJobOfferById(int employerId, int jobId) {
        return "";
    }

    public String editJobOfferById(int employerId, int jobId) {
        return "";
    }

    public boolean deleteJobOfferById(int employerId, int jobId) {
        return false;
    }

    public boolean deleteOfferById(int jobId) {
        return false;
    }

    public void editJobOffer(int jobId) {
    }

    public Job getJobById(int jobId) {
        return JobRepository.jobList.stream().filter(job -> job.getJobId()==jobId).findFirst().orElse(null);
    }
}
