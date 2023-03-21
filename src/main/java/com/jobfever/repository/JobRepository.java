package com.jobfever.repository;

import com.jobfever.model.Employer;
import com.jobfever.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
}

