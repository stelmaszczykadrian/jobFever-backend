package com.jobfever.controller;

import com.jobfever.model.User;
import com.jobfever.repository.UserRepository;
import com.jobfever.service.JobService;
import com.jobfever.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class MainController {

    private UserService userService;
    private UserRepository userRepository;
    private JobService jobService;

    public MainController(JobService jobService) {
//        this.candidateService = candidateService;
        this.jobService = jobService;
    }
    @GetMapping("/")
    public String welcome(){
        return "Welcome on JobFever site";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String submitLoginForm(@RequestBody User user) throws Exception {
        List<User> users = userRepository.findAll();
        for (User i : users){
            if (Objects.equals(user.getEmail(), i.getEmail()) && Objects.equals(user.getPassword(), i.getPassword())){
                return "Logging successful";
            }
        }


        return null;
    }

// Czy lepiej zrobic jeden panel do logowania dla candicates i employers i sprawdzac obie listy, czy raczej dac mozliwosc wyboru kim jestes?


//    GET:       /job/filter/{{filter_by}},{{value}}/{{filter_by}},{{value}}
}
