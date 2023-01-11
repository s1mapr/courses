package com.example.diploma.controllers;

import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.repositories.UserCourseMapRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
