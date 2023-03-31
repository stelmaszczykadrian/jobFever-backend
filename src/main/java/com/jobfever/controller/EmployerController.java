package com.jobfever.controller;

import com.jobfever.model.Employer;
import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/employers")
@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
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

    @PostMapping("/register-employer")
    public void addEmployer(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        employerService.addEmployer(email, password);
    }

    @PutMapping("/{employer_id}")
    public void editProfileById(
            @PathVariable("employer_id") int employerId,
            @RequestBody Employer employer
    ){
        employerService.editProfileById(employerId, employer);
    }
}
