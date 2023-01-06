package com.example.diploma.controllers;

import com.example.diploma.enteties.CourseMaterial;
import com.example.diploma.enteties.Task;
import com.example.diploma.enteties.Variant;
import com.example.diploma.service.CourseMaterialService;
import com.example.diploma.service.TaskService;
import com.example.diploma.service.VariantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(Objects.isNull(variant.getStatus())){
            variant.setStatus(false);
        }
        variant.setTask(task);
        variantService.saveVariant(variant);
    }

    @GetMapping("/task/{id}/variants")
    public List<Variant> getVariantsOfTask(@PathVariable("id") Long id){
        Task task = taskService.getTaskById(id);
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


}
