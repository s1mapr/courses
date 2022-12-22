package com.example.diploma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class StudentController {

    @GetMapping("/mainPage")
    public String getUserMainPage(){
        return "user/mainPage";
    }




}
