package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByFirstName(String firstName);

    User findUserByLoginAndPassword(String login, String password);

    User findUserById(Long id);

    List<User> findUserByRole(Role role);
}

