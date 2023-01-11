package com.example.diploma.service;

import com.example.diploma.enteties.*;
import com.example.diploma.repositories.UserCourseMapRepository;
import com.example.diploma.repositories.UserMaterialMapRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMaterialMapService {

    private UserMaterialMapRepository userMaterialMapRepository;

    private UserCourseMapService userCourseMapService;


    private CourseMaterialService courseMaterialService;

    @Autowired
    public void setUserCourseMapService(UserCourseMapService userCourseMapService) {
        this.userCourseMapService = userCourseMapService;
    }

    @Autowired
    public void setUserMaterialMapRepository(UserMaterialMapRepository userMaterialMapRepository) {
        this.userMaterialMapRepository = userMaterialMapRepository;
    }

    @Autowired
    public void setCourseMaterialService(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }


    public void saveMaterial(UserCourseMaterialMap userCourseMaterialMap){
        userMaterialMapRepository.save(userCourseMaterialMap);
    }

    public void addAllMaterialsUser(User user, Course course) {
        List<CourseMaterial> listOfMaterials = courseMaterialService.getCourseMaterialsByCourse(course);
        new Thread(() -> {
            for(CourseMaterial courseMaterial:listOfMaterials){
                saveMaterial(new UserCourseMaterialMap(new UserCourseMaterialPK(courseMaterial, user), false));
            }
        }).start();
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
}
