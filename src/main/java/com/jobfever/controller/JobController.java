package com.jobfever.controller;

import com.jobfever.model.Job;
import com.jobfever.model.dto.JobDto;
import com.jobfever.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/jobs")
@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class JobController {

    private JobService jobService;
    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping//- > sortowanie od najnowszych
    public List<Job> getAllJobs(){
        return jobService.getAllJobsOffer();
    }


    @PostMapping
    public ResponseEntity<String> createJob(
            @Valid @RequestBody JobDto job, BindingResult bindingResult){

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
            @PathVariable int id
    ){
        return jobService.getJobById(id);
    }

    @PutMapping("/{id}") // zabezpieczyć żeby employer, employerowi nie zmieniał opisu oferty
    public void updateJobOfferById(
            @PathVariable int id,
            @RequestBody Job job
    ){
        jobService.updateJobOffer(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteJobOfferById(
            @PathVariable int id
    ){
        jobService.deleteJobOfferById(id);
    }

    @GetMapping("/")
    public Page<Job> getJobsByPage(@RequestParam int page, String sortBy, String field){

        return jobService.findJobWithPaginationSortedByResponsibilities(page, sortBy, field);
    }
    @GetMapping("/by-employer")
    public Page<Job> getJobsByEmployerId(@RequestParam int id){
        return jobService.findJobByEmployer(id);
    }




}
