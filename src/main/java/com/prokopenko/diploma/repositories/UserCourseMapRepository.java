package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.dto.CourseStatus;
import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.dto.UserCoursePK;
import com.prokopenko.diploma.enteties.Course;
import com.prokopenko.diploma.enteties.User;
import com.prokopenko.diploma.enteties.UserCourseMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseMapRepository extends JpaRepository<UserCourseMap, UserCoursePK> {

     List<UserCourseMap> findUserCourseMapByPk_User(User user);
     List<UserCourseMap> findUserCourseMapByPk_UserAndPkCourseState(User user, CourseStatus state);

     List<UserCourseMap> findUserCourseMapByPk_Course(Course course);

     boolean existsUserCourseMapByPk_UserAndPk_Course(User user, Course course);

     List<UserCourseMap> findUserCourseMapByPkCourseAndPkUserRole(Course course, Role role);

     UserCourseMap findUserCourseMapByPk_CourseAndPk_User(Course course, User user);

     List<UserCourseMap> findAll();
}


