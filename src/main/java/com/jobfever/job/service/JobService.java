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
}
