package com.jobfever.controller;

import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private JobService jobService;
    private EmployerService employerService;

    @GetMapping("/admin/statistics")
    public String getStatistics(){
        return "Statistics";
    }

    @DeleteMapping("/admin/job/{job_id}")
    public boolean deleteJobOffer(
            @PathVariable("job_id") int jobId){
        return jobService.deleteOfferById(jobId);
    }

    @DeleteMapping("/admin/employer/{id}")
    public boolean deleteEmployer(@PathVariable("id") int employerId){
        return employerService.deleteEmployerById(employerId);
    }

    @PutMapping("/admin/job/{job_id}")
    public void editJobOffer(@RequestParam("job_id") int jobId){
        jobService.editJobOffer(jobId);
    }

    @PutMapping("/admin/job/{job_id}")
    public void editEmployerData(@PathVariable("id") int employerId){
        employerService.editEmployerData(employerId);
    }

}
