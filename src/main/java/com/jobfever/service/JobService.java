package com.jobfever.service;

import com.jobfever.model.Candidate;
import com.jobfever.model.Job;
import com.jobfever.model.User;
import com.jobfever.model.dto.JobDto;
import com.jobfever.model.enums.CurrencyType;
import com.jobfever.model.enums.JobType;
import com.jobfever.model.enums.WorkType;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.repository.JobRepository;
import com.jobfever.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class JobService {

    private JobRepository jobRepository;
    private UserRepository userRepository;
    private CandidateRepository candidateRepository;

    @Autowired
    public JobService(JobRepository jobRepository, UserRepository userRepository, CandidateRepository candidateRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
    }

    public void addJobOffer(JobDto jobDto, String email) {

        User user = userRepository.findByEmail(email).orElse(null);
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
                .expirationDate(LocalDateTime.now().plusDays(30))
                .employer_id(user.getEmployer_id())
                .build();

        jobRepository.save(job);
    }

    public Optional<Job> getJobById(int id) {
        return jobRepository.findById(id);
    }

    public void updateJobOffer(int id, JobDto job) {

        Optional<Job> jobToUpdate = getJobById(id);

        jobToUpdate.ifPresent(j -> {
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setTechnicalRequirements(job.getTechnicalRequirements());
            j.setResponsibilities(job.getResponsibilities());
            j.setWhoWeAreLookingFor(job.getWhoWeAreLookingFor());
            j.setBenefits(job.getBenefits());
            j.setLocation(job.getLocation());
            j.setSalaryTo(job.getSalaryTo());
            j.setSalaryFrom(job.getSalaryFrom());
            j.setJobType(JobType.from(job.getJobType()));
            j.setCurrencyType(CurrencyType.from(job.getCurrencyType()));
            j.setWorkType(WorkType.from(job.getWorkType()));
            jobRepository.save(j);

        });
    }

    public void deleteJobOfferById(int id) {
        jobRepository.deleteById(id);
    }


    public Page<Job> findJobByEmployer(int employerId) {
        return new PageImpl<>(jobRepository.findAll()
                .stream()
                .filter(i ->
                        i.getEmployer_id() == employerId)
                .toList());
    }

    public ResponseEntity<String> applyForJobOffer(int id, int candidateId) {
        Optional<Job> jobToUpdate = jobRepository.findById(id);
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.get().getName() == null){
            return new ResponseEntity<>("You have to complete personal information first!", HttpStatus.OK);
        }
        jobToUpdate.ifPresent(j -> {
            j.getCandidateIds().add(candidateId);
        });
        jobRepository.save(jobToUpdate.orElseThrow(() -> new IllegalArgumentException("Job Offer not found with id: " + id)));
        return new ResponseEntity<>("Successfully applied for job.", HttpStatus.OK);

    }

    public Page<Job> findJobsByPageAndSizeDescendingPostingDate(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postingDate")));
    }

    public Page<Job> findJobsByTechnicalRequirementsAndPageAndSize(String language, int page, int size) {
        return jobRepository.findByTechnicalRequirementsContainingIgnoreCase(language,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postingDate")));
    }

    public Page<Job> getJobsBySearchTerm(String searchTerm, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC, "postingDate"));
        Page<Job> jobsPage = jobRepository.findByTitleContainingIgnoreCase(searchTerm, pageable);
        return jobsPage;
    }

    public Page<Job> findJobByCandidateId(int candidateId) {
        return new PageImpl<>(jobRepository.findAll()
                .stream()
                .filter(i ->
                        i.getCandidateIds().contains(candidateId))
                .toList());
    }


    public Set<Integer> findCandidatesByJobId(int jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            return job.getCandidateIds();
        } else {
            return Collections.emptySet();
        }
    }
}



