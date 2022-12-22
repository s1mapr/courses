package com.example.diploma.repositories;

import com.example.diploma.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByFirstName(String firstName);

    User findUserByLoginAndPassword(String login, String password);

}

