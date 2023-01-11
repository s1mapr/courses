package com.example.diploma.repositories;

import com.example.diploma.enteties.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseMapRepository extends JpaRepository<UserCourseMap, UserCoursePK>{

     Double findPk_ProgressByPk_User(User user);

     List<UserCourseMap> findUserCourseMapByPk_User(User user);

     List<UserCourseMap> findSadByPkUserLogin(String login);



     List<UserCourseMap> findUserCourseMapByPk_Course(Course course);

     boolean existsUserCourseMapByPk_UserAndPk_Course(User user, Course course);

     List<UserCourseMap> findUserCourseMapByPkCourseAndPkUserRole(Course course, Role role);
}


