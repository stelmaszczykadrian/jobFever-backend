package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.service.CandidateService;
import com.jobfever.model.Employer;
import com.jobfever.service.EmployerService;
import com.jobfever.model.Job;
import com.jobfever.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private CandidateService candidateService;
    private JobService jobService;
    private EmployerService employerService;

    public MainController(CandidateService candidateService, JobService jobService, EmployerService employerService) {
        this.candidateService = candidateService;
        this.jobService = jobService;
        this.employerService = employerService;
    }
    @GetMapping("/")
    public String welcome(){
        return "Welcome on JobFever site";
    }

    @GetMapping("/jobs")
    public List<Job> jobList(){
        return jobService.getJobList();
    }

    @PostMapping("/register-candidate")
    public Candidate addCandidate(@RequestBody Candidate candidate){
        return candidateService.addCandidate(candidate);
    }

    @PostMapping("/register-employer")
    public Employer addEmployer(@RequestBody Employer employer) {
        return employerService.addEmployer(employer);
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String submitLoginForm(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        if (username.equals("myuser") && password.equals("mypassword")) {
            return "redirect:/jobs";
        } else {
            throw new Exception("Wrong user or password");
        }
    }

    @GetMapping("/jobs/{job_id}")
    public Job getJob(@PathVariable("job_id") int jobId){
        return jobService.getJobById(jobId);
    }


}
