package com.example.diploma.repositories;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.User;
import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.enteties.UserCoursePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseMapRepository extends JpaRepository<UserCourseMap, UserCoursePK>{

     Double findPk_ProgressByPk_User(User user);

     List<UserCourseMap> findUserCourseMapByPk_User(User user);

}


