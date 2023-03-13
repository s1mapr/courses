package com.example.diploma.service;

import com.example.diploma.enteties.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialCourseService {

    private UserCourseMapService userCourseMapService;

    private UserMaterialMapService userMaterialMapService;

    private UserTaskMapService userTaskMapService;

    @Autowired
    public void setUserTaskMapService(UserTaskMapService userTaskMapService) {
        this.userTaskMapService = userTaskMapService;
    }

    @Autowired
    public void setUserCourseMapService(UserCourseMapService userCourseMapService) {
        this.userCourseMapService = userCourseMapService;
    }

    @Autowired
    public void setUserMaterialMapService(UserMaterialMapService userMaterialMapService) {
        this.userMaterialMapService = userMaterialMapService;
    }


    public void updateCourseProgress(User student, CourseMaterial courseMaterial){
        UserCourseMap userCourseMap = userCourseMapService.getUserCourseByCourseAndUser(courseMaterial.getCourse(), student);
        if(!userCourseMap.getStatus()) {
            double materialAmount = userMaterialMapService.getCountOfMaterialsOfCourse(student, courseMaterial);
            double completedMaterialsAmount = userMaterialMapService.getCountOfCompletedMaterialsOfCourse(student, courseMaterial);
            double progress = completedMaterialsAmount / materialAmount * 100;
            String formattedResult = String.format("%.1f", progress).replace(',', '.');
            userCourseMap.setProgress(Double.valueOf(formattedResult));
            userCourseMapService.saveUserCourseMap(userCourseMap);
        }
    }

    public void changeMaterialProgressAndStatusIfNeeded(User student, CourseMaterial courseMaterial, Boolean materialStatus){
        double amountOfAllTasks = userTaskMapService.getCountOfUserTasks(student, courseMaterial);
        double amountOfCorrectAnswers = userTaskMapService.getCountOfCorrectAnswers(student, courseMaterial);
        double progress = amountOfCorrectAnswers / amountOfAllTasks * 100;
        boolean status = materialStatus || progress == 100;
        String formattedResult = String.format("%.1f", progress).replace(',', '.');
        userMaterialMapService.saveMaterial(new UserCourseMaterialMap(
                new UserCourseMaterialPK(courseMaterial, student),
                status,
                Double.valueOf(formattedResult)));
        if(status){
            updateCourseProgress(student, courseMaterial);
        }
    }

}
