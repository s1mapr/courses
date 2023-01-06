package com.example.diploma.controllers;

import com.example.diploma.enteties.Course;
import com.example.diploma.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/mainPage")
    public String getUserMainPage(){
        return "student/mainPage";
    }

    @GetMapping("/courses")
    public String getAllCourses(Model model){
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "student/courses";
    }



}
