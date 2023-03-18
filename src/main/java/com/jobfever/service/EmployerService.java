package com.jobfever.service;

import com.jobfever.model.Employer;
import com.jobfever.repository.EmployerRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {
    public String getEmployerById(int employerId) {
        return "";
    }

    public String getApplicantsByJobOfferById(int employerId, int jobId) {
        return "";
    }
    public boolean deleteEmployerById(int employerId) {
        return false;
    }

    public void editEmployerData(int employerId) {
    }

    public Employer addEmployer(Employer employer) {
        EmployerRepository.employerList.add(employer);
        return employer;
    }



}
