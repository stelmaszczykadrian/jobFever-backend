package com.jobfever.controller;

import com.jobfever.model.Job;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/jobs")
@RestController
public class JobController {

    private JobService jobService;
    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping
    public List<Job> getAllJobs(){
        return jobService.getAllJobsOffer();
    }

    @PostMapping
    public void addJobOffer(){
        jobService.addJobOffer();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobOfferById(
            @PathVariable int id
    ){
        return jobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public void updateJobOfferById(
            @PathVariable int id,
            @RequestBody Job job
    ){
        jobService.updateJobOffer(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteJobOfferById(
            @PathVariable int id
    ){
        jobService.deleteJobOfferById(id);
    }

}
