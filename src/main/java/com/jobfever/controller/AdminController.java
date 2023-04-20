package com.jobfever.controller;

import com.jobfever.model.Admin;
import com.jobfever.service.AdminService;
import com.jobfever.service.EmployerService;
import com.jobfever.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
public class AdminController {

    private JobService jobService;
    private AdminService adminService;
    private EmployerService employerService;
    @Autowired
    public AdminController(JobService jobService, AdminService adminService, EmployerService employerService){
        this.jobService = jobService;
        this.employerService = employerService;
        this.adminService = adminService;
    }
    @GetMapping("/{id}")
    public Optional<Admin> getAdmin(
        @PathVariable("id") int id){
        return adminService.getAdminById(id);
    }

    @GetMapping("/statistics")
    public String getStatistics(){
        return "Statistics";
    }
    @GetMapping("/users")
    public List<Object> getAllUsers(){
        return adminService.getAllUsers();
    }

}
