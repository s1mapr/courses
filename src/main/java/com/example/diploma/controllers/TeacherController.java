package com.example.diploma.controllers;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.*;
import com.example.diploma.repositories.CourseMaterialRepository;
import com.example.diploma.service.CourseMaterialService;
import com.example.diploma.service.CourseService;
import com.example.diploma.service.UserCourseMapService;
import com.example.diploma.utils.YouTubeLinkParser;
import jakarta.servlet.http.HttpServletRequest;
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

    private CourseMaterialService courseMaterialService;

    @Autowired
    public void setCourseMaterialService(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

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

    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable("id") Long id
                            , Model model){
        CourseDTO courseInfo = courseService.getCourseData(id);
        model.addAttribute("courseInfo", courseInfo);
        return "teacher/course";
    }

    @GetMapping("/course/{id}/newMaterial")
    public String addNewMaterialGetMethod(@PathVariable("id") Long id
            ,Model model){
        model.addAttribute("courseMaterial", new CourseMaterial());
        return "teacher/newMaterial";
    }

    @PostMapping("/course/newMaterial")
    public String addNewMaterialPostMethod(
            @ModelAttribute("courseMaterial") CourseMaterial courseMaterial,
            HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("cId"));
        Course course = courseService.getCourseById(id);
        courseMaterial.setCourse(course);
        String videoLink = courseMaterial.getVideoUrl();
        String parsedVideoLink = YouTubeLinkParser.parseLink(videoLink);
        courseMaterial.setVideoUrl(parsedVideoLink);
        courseMaterialService.saveCourseMaterial(courseMaterial);
        return "redirect:"+ id +"/courseMaterial/" + courseMaterial.getCourseMaterialId();
    }

    @GetMapping("/course/{idOfCourse}/courseMaterial/{idOfMaterial}")
    public String getCourseMaterial(@PathVariable("idOfCourse") Long courseId,
                                    @PathVariable("idOfMaterial") Long courseMaterialId,
                                    Model model){
        return "teacher/material";
    }

}
