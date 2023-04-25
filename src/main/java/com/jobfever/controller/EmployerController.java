package com.jobfever.controller;
import com.jobfever.model.Employer;
import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import com.jobfever.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RequestMapping("/api/employers")
@RestController
public class EmployerController {

    private JobService jobService;
    private EmployerService employerService;
    private UserService userService;

    @Autowired
    public EmployerController(JobService jobService, EmployerService employerService, UserService userService) {
        this.jobService = jobService;
        this.employerService = employerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public Optional<Employer> getEmployer(
            @RequestParam("id") int id
    ){
        return employerService.getEmployerById(id);
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

    @PutMapping("/")
    public void editProfileById(
            @RequestParam("id") int employerId,
            @RequestBody Employer employer
    ){
        employerService.editProfileById(employerId, employer);
    }
    @PutMapping("/add-image")
    public void addImageName(
            @RequestParam("id") int id,
            @RequestParam String filename
    ){
        employerService.addFilename(id,filename);
    }
}
