package com.example.diploma.repositories;

import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.enteties.UserCoursePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseMapRepository extends JpaRepository<UserCourseMap, UserCoursePK>{
     @Query("select uc.progress from userСourse uc where uc.pk.user.id=1")
     Double findProgressByUserId(Long userId);
}


