package com.example.diploma.repositories;

import com.example.diploma.enteties.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findCourseById(Long id);

    @Query(value = "select c from course c join userСourse uc where uc.pk.user.id = ?1")
    List<Course> findAllCoursesByUserId(Long userId);


}
