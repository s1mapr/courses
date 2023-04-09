package com.example.diploma.repositories;


import com.example.diploma.enteties.CourseMaterial;
import com.example.diploma.enteties.User;
import com.example.diploma.enteties.UserTaskMap;
import com.example.diploma.enteties.UserTaskPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskMapRepository extends JpaRepository<UserTaskMap, UserTaskPK> {
    List<UserTaskMap> findUserTaskMapByPk_UserAndPk_Task_CourseMaterial(User user, CourseMaterial courseMaterial);

    void deleteByPkTaskCourseMaterial(CourseMaterial courseMaterial);

}
