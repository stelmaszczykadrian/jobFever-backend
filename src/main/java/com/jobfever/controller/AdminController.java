package com.jobfever.controller;

import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import org.springframework.web.bind.annotation.*;
@RequestMapping ("api/admin")
@RestController
public class AdminController {

    private JobService jobService;
    private EmployerService employerService;

    @GetMapping("/statistics")
    public String getStatistics(){
        return "Statistics";
    }

    @DeleteMapping("/job/{job_id}")
    public boolean deleteJobOffer(
            @PathVariable("job_id") int jobId){
        return jobService.deleteOfferById(jobId);
    }

    @DeleteMapping("/employer/{id}")
    public boolean deleteEmployer(@PathVariable("id") int employerId){
        return employerService.deleteEmployerById(employerId);
    }

    @PutMapping("/job/{job_id}")
    public void editJobOffer(@RequestParam("job_id") int jobId){
        jobService.editJobOffer(jobId);
    }


    @PutMapping("/employer/{job_id}")
    public void editEmployerData(@PathVariable("id") int employerId){
        employerService.editEmployerData(employerId);
    }

}
