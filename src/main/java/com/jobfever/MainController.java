package com.jobfever;

import com.jobfever.candidate.model.Candidate;
import com.jobfever.candidate.service.CandidateService;
import com.jobfever.employer.model.Employer;
import com.jobfever.employer.service.EmployerService;
import com.jobfever.job.model.Job;
import com.jobfever.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/candidates")
//    public List<Candidate> candidateList(){
//        return candidateService.getCandidateList();
//    }

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

    @GetMapping("/job/{job_id}")
    public Job getEmployer(@PathVariable("job_id") int jobId){
        return jobService.getJobById(jobId);
    }




// Czy lepiej zrobic jeden panel do logowania dla candicates i employers i sprawdzac obie listy, czy raczej dac mozliwosc wyboru kim jestes?


//    GET:       /job/filter/{{filter_by}},{{value}}/{{filter_by}},{{value}}

}
