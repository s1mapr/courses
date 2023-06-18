package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.entities.Course;
import com.prokopenko.diploma.dto.CourseStatus;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findCourseById(Long id);

    List<Course> findAllCoursesByState(CourseStatus state);



}
