package com.jobfever.service;

import com.jobfever.model.Job;
import com.jobfever.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private JobRepository jobRepository;
    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public List<Job> getAllJobsOffer() {
        return jobRepository.findAll();
    }


    public void addJobOffer() {
        jobRepository.save(new Job(1,"Junior Java Developer","Szukamy do pracy","SQL,JAVA",
                "Cokolwiek","Cokolwiek",
                "Znowu cokolwiek","Kolejny raz","Jeszcze raz",
                5000,"He he", LocalDateTime.now()));
    }


    public Optional<Job> getJobById(int id) {
        return jobRepository.findById(id);
    }

    public void updateJobOffer(int id, Job job) {
        Optional<Job> jobToUpdate = getJobById(id);

        jobToUpdate.ifPresent(j -> {
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setTechnicalRequirements(job.getTechnicalRequirements());
            j.setResponsibilities(job.getResponsibilities());
            j.setWhoWeAreLookingFor(job.getWhoWeAreLookingFor());
            j.setBenefits(job.getBenefits());
            j.setCity(job.getCity());
            j.setAddress(job.getAddress());
            j.setSalary(job.getSalary());
            j.setCurrency(job.getCurrency());
            j.setPostingDate(job.getPostingDate());
        });

        jobRepository.save(jobToUpdate.orElseThrow(() -> new IllegalArgumentException("Job Offer not found with id: " + id)));
    }

    public void deleteJobOfferById(int id) {
        jobRepository.deleteById(id);
    }
}
