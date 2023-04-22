package com.jobfever.controller;

import com.jobfever.model.Job;
import com.jobfever.model.dto.JobDto;
import com.jobfever.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/api/jobs")
@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<String> createJob(
            @Valid @RequestBody JobDto job, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessageBuilder = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessageBuilder.append(error.getDefaultMessage()).append(". ");
            }
            String errorMessage = errorMessageBuilder.toString();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        jobService.addJobOffer(job);
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
            @RequestBody Job job) {
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


    @GetMapping("/by-employer")
    public Page<Job> getJobsByEmployerId(@RequestParam int id) {
        return jobService.findJobByEmployer(id);
    }

    @PutMapping("/{id}/apply")
    public void applyForJobOffer(@PathVariable int id, @RequestParam int candidateId) {
        jobService.applyForJobOffer(id, candidateId);
    }


}
