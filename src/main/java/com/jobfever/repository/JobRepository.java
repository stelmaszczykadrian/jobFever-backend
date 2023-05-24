package com.jobfever.repository;

import com.jobfever.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Page<Job> findByTechnicalRequirementsContainingIgnoreCase(String language, PageRequest postingDate);

    Page<Job> findByTitleContainingIgnoreCase(String searchTerm, Pageable pageable);
}

