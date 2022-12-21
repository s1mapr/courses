package com.example.diploma.repositories;

import com.example.diploma.enteties.StudentCourseMap;
import com.example.diploma.enteties.StudentCoursePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseMapRepository extends JpaRepository<StudentCourseMap, StudentCoursePK>{
     @Query("select s.progress from studentСourse s where s.pk.user.id=1")
     Double findProgressByUserId(Long userId);
}


