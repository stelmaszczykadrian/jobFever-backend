package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.service.CandidateService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/api/candidates")
@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
public class CandidateController {

    private JobService jobService;
    private CandidateService candidateService;
    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateController(JobService jobService, CandidateService candidateService, CandidateRepository candidateRepository){
        this.jobService = jobService;
        this.candidateService = candidateService;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/{candidate_id}")
    public Optional<Candidate> getCandidate(@PathVariable("candidate_id") int candidateId){
        return candidateService.getCandidateById(candidateId);
    }

    @PostMapping("/register")
    public void addCandidate(
            @RequestBody Candidate candidate
    ) {
        candidateService.addCandidate(candidate);
    }

    @PutMapping("/{candidate_id}")
    public void editProfileById(
            @PathVariable("candidate_id") int candidateId,
            @RequestBody Candidate candidate
    ){
        candidateService.editProfileById(candidateId, candidate);
    }
    @PostMapping("/login")
    public String submitLoginForm(@RequestBody Candidate candidate){
        List<Candidate> candidates = candidateRepository.findAll();
        for (Candidate i : candidates){
            if (Objects.equals(candidate.getEmail(), i.getEmail()) &&
                    Objects.equals(candidate.getPassword(), i.getPassword())){
                System.out.println("git");
                return "Logging successful";
            }
        }


        return null;
    }

    @DeleteMapping("/{candidate_id}")
    public boolean deleteCandidateById(@PathVariable("candidate_id") int candidateId){
        return candidateService.deleteCandidateById(candidateId);
    }

    @GetMapping("/{candidate_id}/my-jobs")
    public String getJobsOffersAppliedFor(@PathVariable("candidate_id") int candidateId){
        return candidateService.getJobOffersAppliedForByCandidateId(candidateId);
    }

    @GetMapping("/{candidate_id}/favourites")
    public String getFavouritesJobs(@PathVariable("candidate_id") int candidateId){
        return candidateService.getFavouritesJobsByCandidateId(candidateId);
    }
}