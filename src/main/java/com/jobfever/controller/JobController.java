package com.jobfever.controller;
import com.jobfever.model.Candidate;
import com.jobfever.model.Job;
import com.jobfever.model.dto.JobDto;
import com.jobfever.service.CandidateService;
import com.jobfever.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RequestMapping("/api/jobs")
@RestController
public class JobController {
    private JobService jobService;
    private CandidateService candidateService;

    public JobController(JobService jobService, CandidateService candidateService) {
        this.jobService = jobService;
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<String> createJob(
            @Valid @RequestBody JobDto job, BindingResult bindingResult, @RequestParam String email) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessageBuilder = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessageBuilder.append(error.getDefaultMessage()).append(". ");
            }
            String errorMessage = errorMessageBuilder.toString();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        jobService.addJobOffer(job, email);
        return ResponseEntity.ok("Job added successfully.");
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobOfferById(
            @PathVariable int id) {
        return jobService.getJobById(id);
    }

    @PutMapping("/{id}") // zabezpieczyć żeby employer, employerowi nie zmieniał opisu oferty
    public void updateJobOfferById(
            @PathVariable int id,
            @RequestBody JobDto job
    ){
        jobService.updateJobOffer(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteJobOfferById(
            @PathVariable int id) {
        jobService.deleteJobOfferById(id);
    }


    @GetMapping
    public Page<Job> getJobs(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "") String language) {
        if (language.isEmpty()) {
            return jobService.findJobsByPageAndSizeDescendingPostingDate(page, size);
        } else {
            return jobService.findJobsByTechnicalRequirementsAndPageAndSize(language, page, size);
        }
    }

    @GetMapping("/search")
    public Page<Job> getJobsBySearchTerm(@RequestParam String searchTerm,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return jobService.getJobsBySearchTerm(searchTerm, page, size);
    }

    @GetMapping("/by-employer")
    public Page<Job> getJobsByEmployerId(@RequestParam int id) {
        return jobService.findJobByEmployer(id);
    }

    @GetMapping("/applied-jobs")
    public Page<Job> getJobsByCandidateId(@RequestParam int id) {
        return jobService.findJobByCandidateId(id);
    }

    @GetMapping("/job-applications")
    public List<Job> getJobApplications(@RequestParam(value = "candidateId") int candidateId,
                                        @RequestParam(value = "jobId") int jobId) {
        return jobService.findByCandidateIdAndJobId(candidateId, jobId);
    }

    @PutMapping("/apply")
    public ResponseEntity<?> applyForJobOffer(@RequestParam int id, @RequestParam int candidateId) {
        return jobService.applyForJobOffer(id, candidateId);
    }


    @GetMapping("/{jobId}/candidates")
    public ResponseEntity<Set<Candidate>> getCandidatesByJobId(
            @PathVariable int jobId) {
        Set<Integer> candidateIds = jobService.findCandidatesByJobId(jobId);
        Set<Candidate> candidates = candidateService.findCandidatesByIds(candidateIds);
        return new ResponseEntity<>(candidates, HttpStatus.OK);


    }


}
