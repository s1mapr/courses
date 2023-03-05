package com.example.diploma.repositories;

import com.example.diploma.enteties.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMaterialMapRepository extends JpaRepository<UserCourseMaterialMap, UserCourseMaterialPK> {

    List<UserCourseMaterialMap> findUserCourseMaterialMapByPkCourseMaterialAndPkUserRole(CourseMaterial courseMaterial, Role role);

    UserCourseMaterialMap findByPkUserAndPkCourseMaterial(User user, CourseMaterial courseMaterial);

    List<UserCourseMaterialMap> findUserCourseMaterialMapByPkUserAndPkCourseMaterialCourse(User user, Course course);
}
