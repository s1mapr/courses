package com.prokopenko.diploma.service;


import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.enteties.User;
import com.prokopenko.diploma.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getUserByFirstName(String firstName){
        return userRepository.findUsersByFirstName(firstName);
    }

    public User getUserByLoginAndPassword(String login, String password){
        return userRepository.findUserByLoginAndPassword(login, password);
    }

    public List<User> getUsersByRole(Role role){
        return userRepository.findUserByRole(role);
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

}
