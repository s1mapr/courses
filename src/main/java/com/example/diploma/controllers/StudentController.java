package com.example.diploma.controllers;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.User;
import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.enteties.UserCoursePK;
import com.example.diploma.repositories.UserCourseMapRepository;
import com.example.diploma.service.CourseService;
import com.example.diploma.service.UserCourseMapService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

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

    @GetMapping("/course/{id}")
    public String getCourseById(@PathVariable("id") Long id,
                                Model model,
                                HttpSession session){
        CourseDTO course = courseService.getCourseData(id);
        User user = (User)session.getAttribute("user");
        List<UserCourseMap> userCourseMap = userCourseMapService.getListOfUserCourseMapsByUser(user);
        model.addAttribute("wasBought", !userCourseMap.isEmpty());
        model.addAttribute("courseDTO", course);
        return "student/course";
    }

    @GetMapping("/course/{id}/buy")
    public String buyCourse(@PathVariable("id") Long id,
                                Model model,
                                HttpSession session){

        return "student/buyCourse";
    }

    @PostMapping("/buyCourse")
    public String buyCoursePostMethod(HttpServletRequest request,
                                      HttpSession session){
        Long id = Long.parseLong(request.getParameter("id"));
        Course course = courseService.getCourseById(id);
        User user = (User)session.getAttribute("user");
        UserCourseMap userCourseMap = new UserCourseMap(new UserCoursePK(course, user), 0.0);
        userCourseMapService.saveUserCourseMap(userCourseMap);
        return "redirect:course/"+id;
    }





}
