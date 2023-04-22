package com.jobfever.service;

import com.jobfever.model.Job;
import com.jobfever.model.dto.JobDto;
import com.jobfever.model.enums.CurrencyType;
import com.jobfever.model.enums.JobType;
import com.jobfever.model.enums.WorkType;
import com.jobfever.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public void addJobOffer(JobDto jobDto) {
        Job job = Job.builder()
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .technicalRequirements(jobDto.getTechnicalRequirements())
                .responsibilities(jobDto.getResponsibilities())
                .whoWeAreLookingFor(jobDto.getWhoWeAreLookingFor())
                .benefits(jobDto.getBenefits())
                .location(jobDto.getLocation())
                .salaryFrom(jobDto.getSalaryFrom())
                .salaryTo(jobDto.getSalaryTo())
                .jobType(JobType.from(jobDto.getJobType()))
                .currencyType(CurrencyType.from(jobDto.getCurrencyType()))
                .workType(WorkType.from(jobDto.getWorkType()))
                .postingDate(LocalDateTime.now())
                .build();

        jobRepository.save(job);
    }

    public Optional<Job> getJobById(int id) {
        return jobRepository.findById(id);
    }

    public void updateJobOffer(int id, Job job) {
//        Optional<Job> jobToUpdate = getJobById(id);
//
//        jobToUpdate.ifPresent(j -> {
//            j.setTitle(job.getTitle());
//            j.setDescription(job.getDescription());
//            j.setTechnicalRequirements(job.getTechnicalRequirements());
//            j.setResponsibilities(job.getResponsibilities());
//            j.setWhoWeAreLookingFor(job.getWhoWeAreLookingFor());
//            j.setBenefits(job.getBenefits());
//            j.setCity(job.getCity());
//            j.setAddress(job.getAddress());
//            j.setSalary(job.getSalary());
//            j.setCurrency(job.getCurrency());
//            j.setPostingDate(job.getPostingDate());
//        });
//
//        jobRepository.save(jobToUpdate.orElseThrow(() -> new IllegalArgumentException("Job Offer not found with id: " + id)));
    }

    public void deleteJobOfferById(int id) {
        jobRepository.deleteById(id);
    }


    public Page<Job> findJobByEmployer(int employerId) {
        return new PageImpl<>(jobRepository.findAll()
                .stream()
                .filter(i ->
                        i.getEmployer().getId() == employerId)
                .toList());
    }

    public void applyForJobOffer(int id, int candidateId) {
        Optional<Job> jobToUpdate = jobRepository.findById(id);
        jobToUpdate.ifPresent(j -> {
            j.getCandidateIds().add(candidateId);
        });
        jobRepository.save(jobToUpdate.orElseThrow(() -> new IllegalArgumentException("Job Offer not found with id: " + id)));
    }

    public Page<Job> findJobsByPageAndSizeDescendingPostingDate(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postingDate")));
    }

    public Page<Job> findJobsByTechnicalRequirementsAndPageAndSize(String language, int page, int size) {
        return jobRepository.findByTechnicalRequirementsContainingIgnoreCase(language,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postingDate")));
    }
}



