package com.example.diploma.service;

import com.example.diploma.enteties.*;
import com.example.diploma.repositories.UserMaterialMapRepository;
import com.example.diploma.repositories.UserTaskMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMaterialMapService {

    private UserMaterialMapRepository userMaterialMapRepository;

    private UserCourseMapService userCourseMapService;

    private UserTaskMapRepository userTaskMapRepository;


    @Autowired
    public void setUserTaskMapRepository(UserTaskMapRepository userTaskMapRepository) {
        this.userTaskMapRepository = userTaskMapRepository;
    }

    @Autowired
    public void setUserCourseMapService(UserCourseMapService userCourseMapService) {
        this.userCourseMapService = userCourseMapService;
    }

    @Autowired
    public void setUserMaterialMapRepository(UserMaterialMapRepository userMaterialMapRepository) {
        this.userMaterialMapRepository = userMaterialMapRepository;
    }

    public void saveMaterial(UserCourseMaterialMap userCourseMaterialMap){
        userMaterialMapRepository.save(userCourseMaterialMap);
    }


    public void addMaterialForAllUsers(CourseMaterial courseMaterial){
        Course course = courseMaterial.getCourse();
        List<User> users = userCourseMapService
                .getListOfUserCourseMapsByCourseAndRole(course, Role.STUDENT)
                .stream()
                .map((x)->x.getPk().getUser())
                .toList();
        new Thread(()->{
           for (User user : users){
               saveMaterial(new UserCourseMaterialMap(new UserCourseMaterialPK(courseMaterial, user), false));
           }
        }).start();
    }

    public List<UserCourseMaterialMap> getUserCourseMaterialMapByCourseMaterialAndUserRole(CourseMaterial courseMaterial, Role role){
        return userMaterialMapRepository.findUserCourseMaterialMapByPkCourseMaterialAndPkUserRole(courseMaterial, role);
    }

    public void updateMaterialStatusIfNeeded(CourseMaterial courseMaterial, User user){
        List<UserTaskMap> userTasksList = userTaskMapRepository.findUserTaskMapByPk_UserAndPk_Task_CourseMaterial(user, courseMaterial);
        if(!userTasksList.isEmpty()) {
            int countOfRightAnswers = userTasksList.stream()
                    .map(UserTaskMap::getStatus)
                    .mapToInt(b -> b ? 1 : 0)
                    .sum();
            UserCourseMaterialMap userMaterial = userMaterialMapRepository.findByPkUserAndPkCourseMaterial(user, courseMaterial);
            if (!userMaterial.getStatus() && countOfRightAnswers == userTasksList.size()) {
                userMaterial.setStatus(true);
                saveMaterial(userMaterial);
            }
        }
    }
}
