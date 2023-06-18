package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.dto.UserCourseMaterialPK;
import com.prokopenko.diploma.entities.Course;
import com.prokopenko.diploma.entities.CourseMaterial;
import com.prokopenko.diploma.entities.User;
import com.prokopenko.diploma.entities.UserCourseMaterialMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMaterialMapRepository
    extends JpaRepository<UserCourseMaterialMap, UserCourseMaterialPK> {

    List<UserCourseMaterialMap> findUserCourseMaterialMapByPkCourseMaterialAndPkUserRole(CourseMaterial courseMaterial, Role role);

    UserCourseMaterialMap findByPkUserAndPkCourseMaterial(User user, CourseMaterial courseMaterial);

    List<UserCourseMaterialMap> findUserCourseMaterialMapByPkUserAndPkCourseMaterialCourse(User user, Course course);

    void deleteAllByPkCourseMaterial(CourseMaterial courseMaterial);

}
