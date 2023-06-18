package com.prokopenko.diploma.controllers;

import com.prokopenko.diploma.entities.Task;
import com.prokopenko.diploma.entities.User;
import com.prokopenko.diploma.entities.Variant;
import com.prokopenko.diploma.service.TaskService;import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/course")
@RequiredArgsConstructor
public class TeacherCourseController {

    private final TaskService taskService;

    @GetMapping("/{courseId}/courseMaterial/{materialId}/task/{taskId}")
    public String getTaskPage(@PathVariable("courseId") Long courseId,
                              @PathVariable("taskId") Long taskId,
                              @PathVariable("materialId") Long materialId,
                              Model model,
                              HttpSession session){
        User user = (User)session.getAttribute("user");
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("user", user);
        model.addAttribute("variant", new Variant());
        return "teacher/task";
    }


}
