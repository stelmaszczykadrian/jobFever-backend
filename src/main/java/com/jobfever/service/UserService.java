package com.jobfever.service;

import com.jobfever.model.User;
import com.jobfever.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,"email"));
    }

    public void addUser(String email, String password){
        userRepository.save(new User(email,password));
    }
    public Optional<User> getUserById(int userId){
        return userRepository.findById(userId);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public void resetUserPasswordByEmail(String email, String password){
        if(getUserByEmail(email).isPresent()){
            User updatedUser = getUserByEmail(email).get();
            updatedUser.setPassword(password);
            userRepository.save(updatedUser);
        }
    }
    public boolean deleteUserById(int userId){
        if(getUserById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
