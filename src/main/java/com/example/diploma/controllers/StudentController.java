package com.example.diploma.controllers;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.dto.TaskDTO;
import com.example.diploma.enteties.*;
import com.example.diploma.repositories.UserCourseMapRepository;
import com.example.diploma.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    private CourseService courseService;

    private UserCourseMapService userCourseMapService;

    private CourseMaterialService courseMaterialService;

    private TaskService taskService;

    private VariantService variantService;

    private UserMaterialMapService userMaterialMapService;

    private TaskMaterialService taskMaterialService;

    @Autowired
    public void setTaskMaterialService(TaskMaterialService taskMaterialService) {
        this.taskMaterialService = taskMaterialService;
    }

    @Autowired
    public void setUserMaterialMapService(UserMaterialMapService userMaterialMapService) {
        this.userMaterialMapService = userMaterialMapService;
    }

    @Autowired
    public void setVariantService(VariantService variantService) {
        this.variantService = variantService;
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

    @GetMapping("/mainPage")
    public String getUserMainPage() {
        return "student/mainPage";
    }

    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "student/courses";
    }

    @GetMapping("/course/{id}")
    public String getCourseById(@PathVariable("id") Long id,
                                Model model,
                                HttpSession session) {
        CourseDTO course = courseService.getCourseData(id);
        User user = (User) session.getAttribute("user");
        boolean wasBought = userCourseMapService.checkUserCourseMapIfExist(user, course.getCourse());
        model.addAttribute("wasBought", wasBought);
        model.addAttribute("courseDTO", course);
        return "student/course";
    }

    @GetMapping("/course/{id}/buy")
    public String buyCourse(@PathVariable("id") Long id,
                            Model model,
                            HttpSession session) {

        return "student/buyCourse";
    }

    @PostMapping("/buyCourse")
    public String buyCoursePostMethod(HttpServletRequest request,
                                      HttpSession session) {
        Long id = Long.parseLong(request.getParameter("id"));
        Course course = courseService.getCourseById(id);
        User user = (User) session.getAttribute("user");
        UserCourseMap userCourseMap = new UserCourseMap(new UserCoursePK(course, user), 0.0);
        userCourseMapService.saveUserCourseMap(userCourseMap);
        taskMaterialService.addAllMaterialsUser(user, course);
        return "redirect:course/" + id;
    }

    @GetMapping("/myCourses")
    public String getBoughtCourses(HttpSession session,
                                   Model model) {
        User student = (User) session.getAttribute("user");
        List<UserCourseMap> userCourseMaps = userCourseMapService.getListOfUserCourseMapsByUser(student);
        List<Course> courses = userCourseMaps
                .stream()
                .map(x -> x.getPk().getCourse())
                .toList();
        model.addAttribute("courses", courses);
        return "student/myCourses";
    }


    @GetMapping("/course/{courseId}/courseMaterial/{materialId}")
    public String getCourseMaterial(@PathVariable("courseId") Long courseId,
                                    @PathVariable("materialId") Long materialId) {


        return "student/courseMaterial";
    }


    @GetMapping("/course/{courseId}/courseMaterial/{materialId}/task")
    public String getMaterialTests(@PathVariable("courseId") Long courseId,
                                   @PathVariable("materialId") Long materialId,
                                   Model model) {

        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialByCourseMaterialId(materialId);
        List<Task> tasks = taskService.getMaterialTasks(courseMaterial);
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for(Task task: tasks){
            List<Variant> variants = variantService.getListOfVariants(task);
            taskDTOS.add(new TaskDTO(task, variants));
        }
        model.addAttribute("tasks", taskDTOS);
        return "student/task";
    }
}