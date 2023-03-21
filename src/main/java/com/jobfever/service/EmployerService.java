package com.jobfever.service;

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

//    change this method to use Hibernate! (getApplicantsByJobOfferId)
    public String getApplicantsByJobOfferId(int employerId, int jobId) {
        return "";
    }

    public boolean deleteEmployerById(int employerId) {
        if(getEmployerById(employerId).isPresent()){
            employerRepository.deleteById(employerId);
            return true;
        }
        return false;
    }

    public void editEmployerData(int employerId) {
    }

    public void addEmployer(String email, String password){
        employerRepository.save(new Employer(email,password));
    }

}
