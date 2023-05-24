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
            if (employer.getAboutUs() == null){
                e.setCompanyName(employer.getCompanyName());
                e.setNameAndSurname(employer.getNameAndSurname());
                e.setPhoneNumber(employer.getPhoneNumber());
                e.setLocalization(employer.getLocalization());
                e.setNip(employer.getNip());
                e.setLinkedin(employer.getLinkedin());
                e.setEmail(employer.getEmail());
            }else{
                e.setAboutUs(employer.getAboutUs());
            }
        });
        employerRepository.save(employerToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find employer with id: " + employerId)));
    }

    public void addFilename(int id, String filename) {
        Optional<Employer> employerToUpdate = getEmployerById(id);
        employerToUpdate.ifPresent(e -> {
            e.setImgFileName(filename);
        });
        employerRepository.save(employerToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find employer with id: " + id)));
    }
}
