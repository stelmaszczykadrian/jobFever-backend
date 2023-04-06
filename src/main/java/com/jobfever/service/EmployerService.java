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

    public void addEmployer(Employer employer){
        employerRepository.save(employer);
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
        System.out.println(employer.getAboutUs());
        employerToUpdate.ifPresent(e -> {
            if (employer.getAboutUs() == null){
                e.setCompanyName(employer.getCompanyName());
                e.setNameAndSurname(employer.getNameAndSurname());
                e.setPhoneNumber(employer.getPhoneNumber());
                e.setLocalization(employer.getLocalization());
            }else{
                e.setAboutUs(employer.getAboutUs());
            }


        });
        employerRepository.save(employerToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find employer with id: " + employerId)));
    }

    //    change this method to use Hibernate! (getApplicantsByJobOfferId)
    public String getApplicantsByJobOfferId(int employerId, int jobId) {
        return "";
    }

    public boolean isEmployerExists(String email) {
        Employer employer = employerRepository.findByEmail(email);
        return employer != null;
    }


    public Employer login(String email, String password) {
        Employer existingEmployer = employerRepository.findByEmailAndPassword(email,password);
        if(existingEmployer != null){
            return existingEmployer;
        }else{
            return null;
        }
    }
}
