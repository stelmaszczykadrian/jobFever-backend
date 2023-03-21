package com.jobfever.repository;

import com.jobfever.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    public final static List<Employer> employerList = new ArrayList<>(Arrays.asList(
//            new Employer(1, "Samsung", "password"),
//            new Employer(2, "Motorola", "password"),
//            new Employer(3, "Sony", "password")
    ));
}
