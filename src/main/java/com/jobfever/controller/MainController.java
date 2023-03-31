package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.service.CandidateService;
import com.jobfever.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
public class MainController {

    private CandidateService candidateService;
    private CandidateRepository candidateRepository;
    private JobService jobService;

    public MainController(CandidateService candidateService, JobService jobService) {
        this.candidateService = candidateService;
        this.jobService = jobService;
    }
    @GetMapping("/")
    public String welcome(){
        return "Welcome on JobFever site";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String submitLoginForm(@RequestBody Candidate candidate) throws Exception {
        List<Candidate> candidates = candidateRepository.findAll();
        for (Candidate i : candidates){
            if (Objects.equals(candidate.getEmail(), i.getEmail()) && candidate.getPassword() == i.getPassword()){
                System.out.println("jest");
                return "Logging successful";
            }
        }


        return null;
    }

// Czy lepiej zrobic jeden panel do logowania dla candicates i employers i sprawdzac obie listy, czy raczej dac mozliwosc wyboru kim jestes?


//    GET:       /job/filter/{{filter_by}},{{value}}/{{filter_by}},{{value}}
}
