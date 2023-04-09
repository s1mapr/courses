package com.example.diploma.service;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.*;
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

    private CourseMaterialService courseMaterialService;

    private UserCourseMapRepository userCourseMapRepository;

    @Autowired
    public void setCourseMaterialService(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }




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

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public CourseDTO getCourseData(Long id) {
        Course course = courseRepository.findCourseById(id);
        List<CourseMaterial> courseMaterialList = courseMaterialService.getCourseMaterialsByCourse(course);
        CourseDTO courseInfo = new CourseDTO();
        courseInfo.setCourse(course);
        courseInfo.setCourseMaterials(courseMaterialList);
        return courseInfo;
    }

    public Course getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }


    public List<Course> findAllStartedCourses(){
        return courseRepository.findAllCoursesByState(CourseStatus.STARTED);
    }

}
