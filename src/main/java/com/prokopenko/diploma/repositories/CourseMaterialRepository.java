package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.enteties.Course;
import com.prokopenko.diploma.enteties.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

    List<CourseMaterial> getCourseMaterialsByCourse(Course course);

    CourseMaterial getCourseMaterialByCourseMaterialId(Long id);

}
