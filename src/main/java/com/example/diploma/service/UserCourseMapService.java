package com.example.diploma.service;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.CourseMaterial;
import com.example.diploma.enteties.User;
import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.repositories.UserCourseMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseMapService {

    private UserCourseMapRepository userCourseMapRepository;

    @Autowired
    public void setUserCourseMapRepository(UserCourseMapRepository userCourseMapRepository) {
        this.userCourseMapRepository = userCourseMapRepository;
    }

    public void saveUserCourseMap(UserCourseMap userCourseMap){
        userCourseMapRepository.save(userCourseMap);
    }


    public List<UserCourseMap> getListOfUserCourseMapsByCourse(Course course){

        return userCourseMapRepository.findUserCourseMapByPk_Course(course);
    }
    public List<UserCourseMap> getListOfUserCourseMapsByUser(User user){
        return userCourseMapRepository.findUserCourseMapByPk_User(user);
    }

    public boolean checkUserCourseMapIfExist(User user, Course course){
        return userCourseMapRepository.existsUserCourseMapByPk_UserAndPk_Course(user, course);
    }
}
