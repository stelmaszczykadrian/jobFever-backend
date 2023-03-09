package com.jobfever.employer.service;

import com.jobfever.candidate.model.Candidate;
import com.jobfever.candidate.repository.CandidateRepository;
import com.jobfever.employer.model.Employer;
import com.jobfever.employer.repository.EmployerRepository;
import org.springframework.web.bind.annotation.RequestBody;

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
