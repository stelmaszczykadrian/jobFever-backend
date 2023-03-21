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

    public MainController(CandidateService candidateService, JobService jobService) {
        this.candidateService = candidateService;
        this.jobService = jobService;
    }
    @GetMapping("/")
    public String welcome(){
        return "Welcome on JobFever site";
    }

    @GetMapping("/jobs")
    public List<Job> jobList(){
        return jobService.getJobList();
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

    @GetMapping("/job/{job_id}")
    public Job getJob(@PathVariable("job_id") int jobId){
        return jobService.getJobById(jobId);
    }




// Czy lepiej zrobic jeden panel do logowania dla candicates i employers i sprawdzac obie listy, czy raczej dac mozliwosc wyboru kim jestes?


//    GET:       /job/filter/{{filter_by}},{{value}}/{{filter_by}},{{value}}

}
