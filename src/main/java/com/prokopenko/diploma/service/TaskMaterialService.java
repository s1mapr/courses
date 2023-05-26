package com.prokopenko.diploma.service;

import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.dto.UserCourseMaterialPK;
import com.prokopenko.diploma.dto.UserTaskPK;
import com.prokopenko.diploma.enteties.*;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMaterialService {

    private UserTaskMapService userTaskMapService;

    private UserMaterialMapService userMaterialMapService;

    private CourseMaterialService courseMaterialService;

    @Autowired
    public void setCourseMaterialService(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

    @Autowired
    public void setUserTaskMapService(UserTaskMapService userTaskMapService) {
        this.userTaskMapService = userTaskMapService;
    }


    @Autowired
    public void setUserMaterialMapService(UserMaterialMapService userMaterialMapService) {
        this.userMaterialMapService = userMaterialMapService;
    }

    public void addTaskForAllUsers(Task task) {
    List<User> students =
        userMaterialMapService
            .getUserCourseMaterialMapByCourseMaterialAndUserRole(
                task.getCourseMaterial(), Role.STUDENT)
            .stream()
            .map(x -> x.getPk().getUser())
            .toList();
    new Thread(
            () -> {
              for (User student : students) {
                userTaskMapService.saveUserTaskMap(
                    new UserTaskMap(new UserTaskPK(task, student), false));
              }
            })
        .start();
    }

    public void addAllMaterialsUser(User user, Course course) {
        List<CourseMaterial> listOfMaterials = courseMaterialService.getCourseMaterialsByCourse(course);
    new Thread(
            () -> {
              for (CourseMaterial courseMaterial : listOfMaterials) {
                userMaterialMapService.saveMaterial(
                    new UserCourseMaterialMap(
                        new UserCourseMaterialPK(courseMaterial, user), false, 0.0));
                userTaskMapService.addTasksForUser(courseMaterial, user);
              }
            })
        .start();
    }
}
