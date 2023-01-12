package com.example.diploma.service;

import com.example.diploma.enteties.*;
import com.example.diploma.repositories.UserMaterialMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMaterialMapService {

    private UserMaterialMapRepository userMaterialMapRepository;

    private UserCourseMapService userCourseMapService;



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
}
