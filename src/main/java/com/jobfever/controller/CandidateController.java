package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.model.CandidateEducation;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.service.CandidateService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CandidateController(JobService jobService, CandidateService candidateService, CandidateRepository candidateRepository) {
        this.jobService = jobService;
        this.candidateService = candidateService;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/")
    public Optional<Candidate> getCandidate(@RequestParam("id") int candidateId) {
        return candidateService.getCandidateById(candidateId);
    }

//    @PostMapping()
//    public ResponseEntity<String> addCandidate(
//            @RequestBody Candidate candidate
//    ) {
////        if (candidateService.isCandidateExists(candidate.getEmail())) {
////            return new ResponseEntity<>("Candidate already exists.",
////                    HttpStatus.BAD_REQUEST);
////        }
//        candidateService.addCandidate(candidate);
//        return new ResponseEntity<>("Candidate added successfully.",
//                HttpStatus.OK);
//    }

    @PutMapping("/")
    public void editProfileById(
            @RequestParam("id") int candidateId,
            @RequestBody Candidate candidate
    ) {
        candidateService.editProfileById(candidateId, candidate);
    }

    @PutMapping("/{candidate-id}/education/{education-id}")
    public void editCandidateEducationById(
            @PathVariable("candidate-id") int candidateId,
            @RequestBody CandidateEducation candidateEducation,
            @PathVariable("education-id") int educationId
            ) {
        System.out.println("Candidate Education " + candidateEducation);
        candidateService.editCandidateEducation(candidateId, educationId, candidateEducation);
    }

    @PostMapping("/{candidate-id}/education")
    public void addCandidateEducation(
            @PathVariable("candidate-id") int candidateId,
            @RequestBody CandidateEducation candidateEducation
    ) {
        System.out.println("Candidate Education " + candidateEducation);
        candidateService.addCandidateEducation(candidateId, candidateEducation);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> submitLoginForm(@RequestBody Candidate candidate) {
//        Candidate existingCandidate = candidateService.login(candidate.getEmail(), candidate.getPassword());
//
//        if (existingCandidate != null) {
//            // Perform login logic
//            return new ResponseEntity<>("Login successful.",
//                    HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid email or password.",
//                    HttpStatus.BAD_REQUEST);
//        }
//
//    }

    @DeleteMapping("/{candidate_id}")
    public boolean deleteCandidateById(@PathVariable("candidate_id") int candidateId) {
        return candidateService.deleteCandidateById(candidateId);
    }

    @GetMapping("/{candidate_id}/my-jobs")
    public String getJobsOffersAppliedFor(@PathVariable("candidate_id") int candidateId) {
        return candidateService.getJobOffersAppliedForByCandidateId(candidateId);
    }

    @GetMapping("/{candidate_id}/favourites")
    public String getFavouritesJobs(@PathVariable("candidate_id") int candidateId) {
        return candidateService.getFavouritesJobsByCandidateId(candidateId);
    }
}