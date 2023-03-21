package com.jobfever.repository;

import com.jobfever.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
}
