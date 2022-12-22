package com.example.diploma.controllers;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.User;
import com.example.diploma.service.CourseService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/createCourse")
    public String createCourseGetMethod(Model model){
        model.addAttribute("course", new Course());
        return "teacher/createCourse";
    }

    @PostMapping("/createCourse")
    public String createCoursePostMethod(
            @ModelAttribute("course") Course course,
            HttpSession session){
        User user = (User)session.getAttribute("user");
        String teacherName = user.getFirstName() + " " + user.getLastName();
        course.setTeacherName(teacherName);
        courseService.createCourse(course);
        return "redirect:" + course.getId() + "/editCourse";
    }

    @GetMapping("/{id}/editCourse")
    public String editCourse(@PathVariable("id") Long id){
        return "teacher/editCourse";
    }



}
