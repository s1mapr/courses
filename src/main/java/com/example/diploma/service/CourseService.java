package com.example.diploma.service;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.User;
import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.repositories.CourseRepository;
import com.example.diploma.repositories.UserCourseMapRepository;
import com.example.diploma.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private UserRepository userRepository;



    private UserCourseMapRepository userCourseMapRepository;

    @Autowired
    public void setUserCourseMapRepository(UserCourseMapRepository userCourseMapRepository) {
        this.userCourseMapRepository = userCourseMapRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(Course course){
        courseRepository.save(course);
    }

    public CourseDTO getCourseData(Long id){
        Course course = courseRepository.findCourseById(id);
        CourseDTO courseInfo = new CourseDTO();
        courseInfo.setCourseTitle(course.getCourseTitle());
        courseInfo.setTeacherName(course.getTeacherName());
        courseInfo.setPrice(course.getPrice());
        return courseInfo;
    }

    public List<UserCourseMap> getAllUserCourses(Long id){
        User user = userRepository.findUserById(id);
        return userCourseMapRepository.findUserCourseMapByPk_User(user);
    }
}
