package com.jobfever.controller;

import com.jobfever.model.Employer;
import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/employers")
@RestController
public class EmployerController {

    private JobService jobService;
    private EmployerService employerService;

    @Autowired
    public EmployerController(JobService jobService, EmployerService employerService) {
        this.jobService = jobService;
        this.employerService = employerService;
    }

    @GetMapping("/{employer_id}")
    public Optional<Employer> getEmployer(
            @PathVariable("employer_id") int employerId
    ){
        return employerService.getEmployerById(employerId);
    }

    @PostMapping("/{employer_id}/job/{job_id}/add")
    public String addJobOffer(
            @PathVariable("job_id") int jobId,
            @PathVariable("employer_id") int employerId
    ){
        return jobService.addJobOfferById(employerId,jobId);
    }

    @PutMapping("/{employer_id}/job/{job_id}/edit")
    public String editJobOfferById(
            @PathVariable("job_id") int jobId,
            @PathVariable("employer_id") int employerId
    ){
        return jobService.editJobOfferById(employerId,jobId);
    }

    @DeleteMapping("/{employer_id}/job/{job_id}/delete")
    public boolean deleteJobOfferById(
            @PathVariable("job_id") int jobId,
            @PathVariable("employer_id") int employerId
    ){
        return jobService.deleteJobOfferById(employerId,jobId);
    }

    @GetMapping("/{employer_id}/job/{job_id}/applicants")
    public String getJobOfferApplicants(
            @PathVariable("job_id") int jobId,
            @PathVariable("employer_id") int employerId
    ){
        return employerService.getApplicantsByJobOfferId(employerId,jobId);
    }

    @DeleteMapping("/{employer_id}")
    public boolean deleteEmployerById(
            @PathVariable("employer_id") int employerId
    ){
        return employerService.deleteEmployerById(employerId);
    }

        @PostMapping("/add_employer")
    public void addEmployer(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        employerService.addEmployer(email, password);
    }

    //TODO Make this work :D
    @PutMapping("/employer/{id}")
    public void editEmployerData(@PathVariable("id") int employerId){
        employerService.editEmployerData(employerId);
    }




}
