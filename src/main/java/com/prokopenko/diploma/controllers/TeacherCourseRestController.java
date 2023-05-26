package com.prokopenko.diploma.controllers;


import com.prokopenko.diploma.enteties.Task;
import com.prokopenko.diploma.enteties.User;
import com.prokopenko.diploma.enteties.Variant;
import com.prokopenko.diploma.service.TaskService;
import com.prokopenko.diploma.service.VariantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/teacher/course")
public class TeacherCourseRestController {

    private TaskService taskService;

    private VariantService variantService;

    @Autowired
    public void setVariantService(VariantService variantService) {
        this.variantService = variantService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createNewVariant")
    public void createNewVariant(HttpServletRequest request,
                                   @ModelAttribute("variant") Variant variant){
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(taskId);
        variant.setTask(task);
        variantService.saveVariant(variant);
    }

    @GetMapping("/task/{id}/variants")
    public List<Variant> getVariantsOfTask(@PathVariable("id") Long id,
                                           HttpSession session,
                                           Model model){
        User user = (User)session.getAttribute("user");
        Task task = taskService.getTaskById(id);
        model.addAttribute("user", user);
        return variantService.getListOfVariants(task);
    }

    @PostMapping("/updateVariant")
    public void updateVariant(HttpServletRequest request,
                              @ModelAttribute("variant") Variant variant){
        Long id = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(id);
        if(Objects.isNull(variant.getStatus())){
            variant.setStatus(false);
        }
        variant.setTask(task);
        variantService.saveVariant(variant);
    }

    @PostMapping("/deleteVariant")
    public void removeVariant(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        variantService.deleteVariantById(id);
    }
}
