package com.jobfever.controller;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobfever.model.Employer;
import com.jobfever.model.User;
import com.jobfever.role.RoleType;
import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import com.jobfever.service.UserService;
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


    @PutMapping("/")
    public void editProfileById(
            @RequestParam("id") int employerId,
            @RequestBody Employer employer
    ){
        employerService.editProfileById(employerId, employer);
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> submitLoginForm(@RequestBody Employer employer){
//        Employer existingEmployer = employerService.login(employer.getEmail(),employer.getPassword());
//        if (existingEmployer != null) {
//            // Perform login logic
//            return new ResponseEntity<>("Login successful.",
//                    HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid email or password.",
//                    HttpStatus.BAD_REQUEST);
//        }
//
//    }


}
