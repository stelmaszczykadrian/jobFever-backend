package com.jobfever.service;
import com.jobfever.model.Job;
import com.jobfever.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

    public void addJobOffer(Job job) {
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


    public Page<Job> findJobWithPaginationSortedByResponsibilities(int page, String sortBy, String field){
        return new PageImpl<>(jobRepository.findAll(PageRequest.of(page, 10, Sort.by(sortBy))).stream()
                .filter(i -> i.getResponsibilities().contains(field)).toList());

    }
    public Page<Job> findJobByEmployer(int employerId){
        return new PageImpl<>(jobRepository.findAll()
                .stream()
                .filter(i ->
                        i.getEmployer().getId() == employerId)
                .toList());
    }

    public void applyForJobOffer(int id, int candidateId){
        Optional<Job> jobToUpdate = jobRepository.findById(id);
        jobToUpdate.ifPresent(j -> {
            j.getCandidateIds().add(candidateId);
        });
        jobRepository.save(jobToUpdate.orElseThrow(() -> new IllegalArgumentException("Job Offer not found with id: " + id)));
    }
}



