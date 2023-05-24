package com.jobfever.service;

import com.jobfever.model.Admin;
import com.jobfever.repository.AdminRepository;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private CandidateRepository candidateRepository;
    private EmployerRepository employerRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, EmployerRepository employerRepository) {
        this.adminRepository = adminRepository;
        this.employerRepository = employerRepository;
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public List<Object> getAllUsers() {
        List<Object> allUsers = new ArrayList<>(employerRepository.findAll());
        return allUsers;
    }

    public void addAdmin(String login, String password) {
        adminRepository.save(new Admin(login, password));
    }
}
