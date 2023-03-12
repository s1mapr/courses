package com.example.diploma.controllers;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.dto.TaskDTO;
import com.example.diploma.enteties.*;
import com.example.diploma.service.*;
import com.example.diploma.utils.CourseFilter;
import com.example.diploma.utils.PdfGenerator;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private UserService userService;

    private CourseService courseService;

    private UserCourseMapService userCourseMapService;

    private CourseMaterialService courseMaterialService;

    private TaskService taskService;

    private VariantService variantService;


    private TaskMaterialService taskMaterialService;

    private UserMaterialMapService userMaterialMapService;

    @Autowired
    public void setUserMaterialMapService(UserMaterialMapService userMaterialMapService) {
        this.userMaterialMapService = userMaterialMapService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTaskMaterialService(TaskMaterialService taskMaterialService) {
        this.taskMaterialService = taskMaterialService;
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
    public String getAllCourses(Model model,
                                @ModelAttribute("filter") Filter filter,
                                HttpSession session) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        List<Course> courses = courseService.findAllCourses();
        courses = CourseFilter.doFilter(courses, filter, "");
        List<User> teachers = userService.getUsersByRole(Role.TEACHER);
        model.addAttribute("teachers", teachers);
        model.addAttribute("courses", courses);
        model.addAttribute("filter", filter);
        return "student/courses";
    }

    @GetMapping("/course/{id}")
    public String getCourseById(@PathVariable("id") Long id,
                                Model model,
                                HttpSession session) {
        CourseDTO course = courseService.getCourseData(id);
        User user = (User) session.getAttribute("user");
        boolean wasBought = userCourseMapService.checkUserCourseMapIfExist(user, course.getCourse());
        //userCourseMapService.updateCourseStatusIfNeeded(course.getCourse(), user);
        if(wasBought){
            UserCourseMap userCourseMap = userCourseMapService.getUserCourseByCourseAndUser(course.getCourse(), user);
            course.setStatus(userCourseMap.getStatus());
            course.setProgress(userCourseMap.getProgress());
        }
        model.addAttribute("user", user);
        model.addAttribute("wasBought", wasBought);
        model.addAttribute("courseDTO", course);
        return "student/course";
    }


    public String filterCourses() {
        return null;
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
        UserCourseMap userCourseMap = new UserCourseMap(new UserCoursePK(course, user), false, 0.0);
        userCourseMapService.saveUserCourseMap(userCourseMap);
        taskMaterialService.addAllMaterialsUser(user, course);
        return "redirect:course/" + id;
    }

    @GetMapping("/myCourses")
    public String getBoughtCourses(HttpSession session,
                                   Model model) {
        User student = (User)session.getAttribute("user");
        model.addAttribute("user", student);
        List<UserCourseMap> userCourseMaps = userCourseMapService.getListOfUserCourseMapsByUser(student);
//        List<Course> courses = userCourseMaps
//                .stream()
//                .map(x -> x.getPk().getCourse())
//                .toList();
        model.addAttribute("courses", userCourseMaps);
        return "student/myCourses";
    }


    @GetMapping("/course/{courseId}/courseMaterial/{materialId}")
    public String getCourseMaterial(@PathVariable("courseId") Long courseId,
                                    @PathVariable("materialId") Long materialId,
                                    HttpSession session,
                                    Model model) {
        User student = (User)session.getAttribute("user");
        model.addAttribute("user", student);
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialByCourseMaterialId(materialId);
        userMaterialMapService.updateMaterialStatusIfNeeded(courseMaterial, student);
        return "student/courseMaterial";
    }


    @GetMapping("/course/{courseId}/courseMaterial/{materialId}/task")
    public String getMaterialTests(@PathVariable("courseId") Long courseId,
                                   @PathVariable("materialId") Long materialId,
                                   Model model) {

        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialByCourseMaterialId(materialId);
        List<Task> tasks = taskService.getMaterialTasks(courseMaterial);
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (Task task : tasks) {
            List<Variant> variants = variantService.getListOfVariants(task);
            taskDTOS.add(new TaskDTO(task, variants));
        }
        model.addAttribute("tasks", taskDTOS);
        return "student/task";
    }

    @GetMapping("/{userId}/profile")
    public static String studentProfile(@PathVariable("userId") Long userId,
                                        HttpSession session,
                                        Model model) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        return "student/profile";
    }

    @GetMapping("/download-pdf")
    public void downloadPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=example.pdf");
        ByteArrayOutputStream baos = PdfGenerator.generatePdf();
        response.getOutputStream().write(baos.toByteArray());
        response.getOutputStream().flush();
    }

}
