package com.jobfever.controller;
import com.jobfever.model.Employer;
import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RequestMapping("/api/employers")
@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class EmployerController {

    private JobService jobService;
    private EmployerService employerService;

    @Autowired
    public EmployerController(JobService jobService, EmployerService employerService) {
        this.jobService = jobService;
        this.employerService = employerService;
    }

    @GetMapping("/")
    public Optional<Employer> getEmployer(
            @RequestParam("id") int id
    ){
        System.out.println("jestem tu");
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

    @PostMapping()
    public ResponseEntity<String> addEmployer(
            @RequestBody Employer employer
    ) {
        if(employerService.isEmployerExists(employer.getEmail())){
            return new ResponseEntity<>("Employer already exists.",
                    HttpStatus.BAD_REQUEST);
        }
        employerService.addEmployer(employer);
        return new ResponseEntity<>("Employer added successfully.",
                HttpStatus.OK);
    }

    @PutMapping("/")
    public void editProfileById(
            @RequestParam("id") int employerId,
            @RequestBody Employer employer
    ){
        employerService.editProfileById(employerId, employer);
    }


    @PostMapping("/login")
    public ResponseEntity<String> submitLoginForm(@RequestBody Employer employer){
        Employer existingEmployer = employerService.login(employer.getEmail(),employer.getPassword());
        if (existingEmployer != null) {
            // Perform login logic
            return new ResponseEntity<>("Login successful.",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password.",
                    HttpStatus.BAD_REQUEST);
        }

    }


}
