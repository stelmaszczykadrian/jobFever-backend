package com.jobfever.controller;

import com.jobfever.repository.UserRepository;
import com.jobfever.service.JobService;
import com.jobfever.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
public class MainController {

    private UserService userService;
    private UserRepository userRepository;
    private JobService jobService;

    public MainController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/")
    public String welcome(){
        return "Welcome on JobFever site";
    }


}
