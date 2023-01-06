package com.example.diploma.controllers;

import com.example.diploma.enteties.Variant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ValueConstants;

@Controller
@RequestMapping("/teacher/course")
public class TeacherCourseController {

    @GetMapping("/{courseId}/courseMaterial/{materialId}/task/{taskId}")
    public String getTaskPage(@PathVariable("courseId") Long courseId,
                              @PathVariable("taskId") Long taskId,
                              @PathVariable("materialId") Long materialId,
                              Model model){
        model.addAttribute("variant", new Variant());
        return "teacher/task";
    }


}
