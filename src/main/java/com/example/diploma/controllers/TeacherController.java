package com.example.diploma.controllers;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.User;
import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.enteties.UserCoursePK;
import com.example.diploma.service.CourseService;
import com.example.diploma.service.UserCourseMapService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private CourseService courseService;

    private UserCourseMapService userCourseMapService;

    @Autowired
    public void setUserCourseMapService(UserCourseMapService userCourseMapService) {
        this.userCourseMapService = userCourseMapService;
    }

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
        userCourseMapService.saveUserCourseMap(new UserCourseMap(new UserCoursePK(course, user)));
        return "redirect:" + course.getId() + "/course";
    }

    @GetMapping("courses")
    public String getTeacherCourses(Model model,
                                    HttpSession session){
        User user = (User)session.getAttribute("user");
        List<UserCourseMap> courses = courseService.getAllUserCourses(user.getId());
        model.addAttribute("courses", courses);
        return "teacher/courses";
    }

    @GetMapping("/{id}/course")
    public String getCourse(@PathVariable("id") Long id
                            , Model model){
        CourseDTO courseInfo = courseService.getCourseData(id);
        model.addAttribute("courseInfo", courseInfo);
        return "teacher/course";
    }

    @GetMapping
    public String addNewMaterialGetMethod(){
        return "teacher/newMaterial";
    }





}
