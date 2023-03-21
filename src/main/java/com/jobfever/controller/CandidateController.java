package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.service.CandidateService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/candidates")
@RestController
public class CandidateController {

    private JobService jobService;
    private CandidateService candidateService;

    @Autowired
    public CandidateController(JobService jobService, CandidateService candidateService){
        this.jobService = jobService;
        this.candidateService = candidateService;
    }

    @GetMapping("/{candidate_id}")
    public Optional<Candidate> getCandidate(@PathVariable("candidate_id") int candidateId){
        return candidateService.getCandidateById(candidateId);
    }

    @PostMapping("/register-candidate")
    public void addCandidate(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        candidateService.addCandidate(email, password);
    }

    @PutMapping("/{candidate_id}")
    public void editProfileById(
            @PathVariable("candidate_id") int candidateId,
            @RequestBody Candidate candidate
    ){
        candidateService.editProfileById(candidateId, candidate);
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