package com.jobfever.controller;

import com.jobfever.model.Candidate;
import com.jobfever.model.CandidateEducation;
import com.jobfever.model.CandidateExperience;
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
        candidateService.editCandidateEducation(candidateId, educationId, candidateEducation);
    }

    @PostMapping("/{candidate-id}/education")
    public int addCandidateEducation(
            @PathVariable("candidate-id") int candidateId,
            @RequestBody CandidateEducation candidateEducation
    ) {
        return candidateService.addCandidateEducation(candidateId, candidateEducation);
    }

    @PutMapping("/{candidate-id}/experience/{experience-id}")
    public void editCandidateExperienceById(
            @PathVariable("candidate-id") int candidateId,
            @RequestBody CandidateExperience candidateExperience,
            @PathVariable("experience-id") int experienceId
    ) {
        candidateService.editCandidateExperience(candidateId, experienceId, candidateExperience);
    }

    @PostMapping("/{candidate-id}/experience")
    public int addCandidateExperience(
            @PathVariable("candidate-id") int candidateId,
            @RequestBody CandidateExperience candidateExperience
    ) {
        return candidateService.addCandidateExperience(candidateId, candidateExperience);
    }

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