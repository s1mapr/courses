package com.example.diploma.service;


import com.example.diploma.enteties.User;
import com.example.diploma.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private UserRepository userRepository;

    @Autowired
    public void setStudentRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getStudentsByFirstName(String firstName){
        return userRepository.findStudentsByFirstName(firstName);
    }

    public User getStudentByLoginAndPassword(String login, String password){
        return userRepository.findStudentByLoginAndPassword(login, password);
    }
}
