package com.jobfever.repository;

import com.jobfever.model.Employer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface EmployerRepository {

    public final static List<Employer> employerList = new ArrayList<>(Arrays.asList(
            new Employer(1, "Samsung"),
            new Employer(2, "Motorola"),
            new Employer(3, "Sony")
    ));
}
