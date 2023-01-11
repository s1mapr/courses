package com.example.diploma.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

    @PostMapping("/student/course/checkAnswers")
    public void checkAnswers(HttpServletRequest request){
        System.out.println(request.getParameter("taskId"));
        System.out.println(request.getParameter("taskAnswer"));
    }

}
