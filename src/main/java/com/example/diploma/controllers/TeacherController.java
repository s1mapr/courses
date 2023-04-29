package com.example.diploma.controllers;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.*;
import com.example.diploma.repositories.UserRepository;
import com.example.diploma.service.*;
import com.example.diploma.utils.YouTubeLinkParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private CourseService courseService;

    private UserCourseMapService userCourseMapService;

    private CourseMaterialService courseMaterialService;

    private TaskService taskService;

    private UserMaterialMapService userMaterialMapService;

    private TaskMaterialService taskMaterialService;

    private AWSService awsService;

    @Autowired
    public void setAwsService(AWSService awsService) {
        this.awsService = awsService;
    }

    @Autowired
    public void setTaskMaterialService(TaskMaterialService taskMaterialService) {
        this.taskMaterialService = taskMaterialService;
    }

    @Autowired
    public void setUserMaterialMapService(UserMaterialMapService userMaterialMapService) {
        this.userMaterialMapService = userMaterialMapService;
    }


    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

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
    public String createCourseGetMethod(Model model,
                                        HttpSession session) {
        User student = (User)session.getAttribute("user");
        model.addAttribute("user", student);
        model.addAttribute("course", new Course());
        return "teacher/createCourse";
    }

    @PostMapping("/createCourse")
    public String createCoursePostMethod(
            @ModelAttribute("course") Course course,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        String teacherName = user.getFirstName() + " " + user.getLastName();
        course.setTeacherName(teacherName);
        course.setPictureUrl("/images/defaultCourseImg.jpg");
        courseService.saveCourse(course);
        userCourseMapService.saveUserCourseMap(new UserCourseMap(new UserCoursePK(course, user)));
        return "redirect:course/" + course.getId();
    }

    @GetMapping("/courses")
    public String getTeacherCourses(Model model,
                                    HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Course> courses = userCourseMapService
                .getListOfUserCourseMapsByUserForTeacher(user)
                .stream()
                .map(x-> x.getPk().getCourse())
                .toList();
        model.addAttribute("user", user);
        model.addAttribute("courses", courses);
        return "teacher/courses";
    }

    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable("id") Long id,
                            Model model,
                            HttpServletRequest request,
                            HttpSession session) {
        User user = (User)session.getAttribute("user");
        CourseDTO courseInfo = courseService.getCourseData(id);
        model.addAttribute("user", user);
        model.addAttribute("courseInfo", courseInfo);
        return "teacher/course";
    }

    @GetMapping("/course/{id}/newMaterial")
    public String addNewMaterialGetMethod(@PathVariable("id") Long id,
                                          Model model,
                                          HttpSession session) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("courseMaterial", new CourseMaterial());
        return "teacher/newMaterial";
    }

    @PostMapping("/course/newMaterial")
    public String addNewMaterialPostMethod(
            @ModelAttribute("courseMaterial") CourseMaterial courseMaterial,
            HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("cId"));
        Course course = courseService.getCourseById(id);
        courseMaterial.setCourse(course);
        String videoLink = courseMaterial.getVideoUrl();
        String parsedVideoLink = YouTubeLinkParser.parseLink(videoLink);
        courseMaterial.setVideoUrl(parsedVideoLink);
        courseMaterialService.saveCourseMaterial(courseMaterial);
        userMaterialMapService.addMaterialForAllUsers(courseMaterial);
        return "redirect:" + id + "/courseMaterial/" + courseMaterial.getCourseMaterialId();
    }

    @GetMapping("/course/{idOfCourse}/courseMaterial/{idOfMaterial}")
    public String getCourseMaterial(@PathVariable("idOfCourse") Long courseId,
                                    @PathVariable("idOfMaterial") Long courseMaterialId,
                                    Model model, HttpSession session) {
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialByCourseMaterialId(courseMaterialId);
        List<Task> listOfTasks = taskService.getMaterialTasks(courseMaterial);
        User user = (User)session.getAttribute("user");
        model.addAttribute("courseMaterial", courseMaterial);
        model.addAttribute("task", new Task());
        model.addAttribute("variant", new Variant());
        model.addAttribute("tasks", listOfTasks);
        model.addAttribute("user", user);
        return "teacher/material";
    }

    @PostMapping("/createNewTask")
    public String createNewTask(@ModelAttribute("task") Task task,
                                HttpServletRequest request) {
        long courseId = Long.parseLong(request.getParameter("cId"));
        long materialId = Long.parseLong(request.getParameter("cmId"));
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialByCourseMaterialId(materialId);
        task.setCourseMaterial(courseMaterial);
        taskService.saveTask(task);
        taskMaterialService.addTaskForAllUsers(task);
        return "redirect:course/" + courseId + "/courseMaterial/" + materialId + "/task/" + task.getId();
    }


    @PostMapping("/uploadCourseImg")
    public String uploadCourseImage(@RequestParam("img") MultipartFile multipartFile,
                                    HttpServletRequest request) throws IOException {
        Long courseId = Long.parseLong(request.getParameter("cId"));
        String url = awsService.putElement(multipartFile, courseId, "courseImg/");
        Course course = courseService.getCourseById(courseId);
        course.setPictureUrl(url);
        courseService.saveCourse(course);
        return "redirect:course/" + courseId;
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") Long id,
                               HttpSession session,
                               Model model){
        User user = (User)session.getAttribute("user");
        Course course = courseService.getCourseById(id);
        course.setState(CourseStatus.DELETED);
        courseService.saveCourse(course);
        model.addAttribute("user", user);
        return "redirect:/teacher/courses";
    }

    @GetMapping("/course/{courseId}/deleteMaterial/{id}")
    public String deleteMaterial(@PathVariable("id") Long id,
                                 @PathVariable("courseId") Long courseId,
                                 Model model,
                                 HttpSession session){
        User user = (User)session.getAttribute("user");
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialByCourseMaterialId(id);
        courseMaterialService.deleteCourseMaterial(courseMaterial);
        model.addAttribute("user", user);
        return "redirect:/teacher/course/" + courseId;
    }

    @GetMapping("/editCourse/{id}")
    public String editCourse(@PathVariable("id") Long courseId,
                             Model model,
                             HttpSession session) {
        User user = (User)session.getAttribute("user");
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("user", user);
        model.addAttribute("course", course);
        return "teacher/editCourse";
    }

    @PostMapping("/editCourse")
    public String editCoursePostMethod(@ModelAttribute("course") Course course,
                                       HttpSession session){
        User user = (User)session.getAttribute("user");
        courseService.saveCourse(course);
        return "redirect:/teacher/course/"+course.getId();
    }

}
