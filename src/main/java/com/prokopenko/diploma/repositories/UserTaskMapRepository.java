package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.entities.CourseMaterial;
import com.prokopenko.diploma.entities.User;
import com.prokopenko.diploma.entities.UserTaskMap;
import com.prokopenko.diploma.dto.UserTaskPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskMapRepository extends JpaRepository<UserTaskMap, UserTaskPK> {
    List<UserTaskMap> findUserTaskMapByPk_UserAndPk_Task_CourseMaterial(User user, CourseMaterial courseMaterial);

    void deleteByPkTaskCourseMaterial(CourseMaterial courseMaterial);

}
