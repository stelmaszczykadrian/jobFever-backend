package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.service.CandidateService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping()
    public ResponseEntity<String> addCandidate(
            @RequestBody Candidate candidate
    ) {
        if(candidateService.isCandidateExists(candidate.getEmail())){
            return new ResponseEntity<>("Candidate already exists.",
                    HttpStatus.BAD_REQUEST);
        }
        candidateService.addCandidate(candidate);
        return new ResponseEntity<>("Candidate added successfully.",
                HttpStatus.OK);
    }

    @PutMapping("/{candidate_id}")
    public void editProfileById(
            @PathVariable("candidate_id") int candidateId,
            @RequestBody Candidate candidate
    ){
        candidateService.editProfileById(candidateId, candidate);
    }
    @PostMapping("/login")
    public ResponseEntity<String> submitLoginForm(@RequestBody Candidate candidate){
        Candidate existingCandidate = candidateService.login(candidate.getEmail(),candidate.getPassword());

        if (existingCandidate != null) {
            // Perform login logic
            return new ResponseEntity<>("Login successful.",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password.",
                    HttpStatus.BAD_REQUEST);
        }

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