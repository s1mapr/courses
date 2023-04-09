package com.example.diploma.repositories;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.CourseStatus;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findCourseById(Long id);

    List<Course> findAllCoursesByState(CourseStatus state);



}
