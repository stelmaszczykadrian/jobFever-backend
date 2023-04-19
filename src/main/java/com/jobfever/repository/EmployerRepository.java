package com.jobfever.repository;

import com.jobfever.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

//    Employer findByEmail(String email);

//    Employer findByEmailAndPassword(String email, String password);

    Employer findByCompanyName(String companyName);
}
