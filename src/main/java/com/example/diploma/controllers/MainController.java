package com.example.diploma.controllers;

import com.example.diploma.enteties.Student;
import com.example.diploma.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private StudentService service;

    @Autowired
    public void setService(StudentService service) {
        this.service = service;
    }

    @GetMapping("/mainPage")
    public String mainPage(){
        return "mainPage";
    }


    @GetMapping("/register")
    public String registrationGetMethod(Model model){
        model.addAttribute("user", new Student());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPostMethod(@ModelAttribute("user") Student student){
        service.saveUser(student);
        return "redirect:authorization";
    }

    @GetMapping("authorization")
    public String authorizationGetMethod(Model model){
        //model.addAttri
        return "authorization";
    }

}
