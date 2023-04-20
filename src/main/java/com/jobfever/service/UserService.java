package com.jobfever.service;

import com.jobfever.model.User;
import com.jobfever.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository =userRepository;}

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean isUserExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}
