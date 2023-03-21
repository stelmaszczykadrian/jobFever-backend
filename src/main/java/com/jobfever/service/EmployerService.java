package com.jobfever.service;

import com.jobfever.model.Candidate;
import com.jobfever.model.Employer;
import com.jobfever.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {
    private EmployerRepository employerRepository;
    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Optional<Employer> getEmployerById(int employerId){
        return employerRepository.findById(employerId);
    }

    public void addEmployer(String email, String password){
        employerRepository.save(new Employer(email,password));
    }

    public boolean deleteEmployerById(int employerId) {
        if(getEmployerById(employerId).isPresent()){
            employerRepository.deleteById(employerId);
            return true;
        }
        return false;
    }

    public void editProfileById(int employerId, Employer employer){
        Optional<Employer> employerToUpdate = getEmployerById(employerId);

        employerToUpdate.ifPresent(e -> {
            e.setEmail(employer.getEmail());
            e.setPassword(employer.getPassword());
        });

        employerRepository.save(employerToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find employer with id: " + employerId)));
    }

    //    change this method to use Hibernate! (getApplicantsByJobOfferId)
    public String getApplicantsByJobOfferId(int employerId, int jobId) {
        return "";
    }
}
