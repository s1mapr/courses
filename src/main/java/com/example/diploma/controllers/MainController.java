package com.example.diploma.controllers;

import com.example.diploma.enteties.Role;
import com.example.diploma.enteties.User;
import com.example.diploma.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String getMainPage(){
        return "redirect:mainPage";
    }


    @GetMapping("/mainPage")
    public String mainPage(Model model){
        List<User> user = service.getUserByFirstName("Maksym");
        model.addAttribute("students", user);
        return "mainPage";
    }


    @GetMapping("/registration")
    public String registrationGetMethod(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPostMethod(@ModelAttribute("user") User user){
        user.setStatus(true);
        user.setRole(Role.STUDENT);
        service.saveUser(user);
        return "redirect:authorization";
    }

    @GetMapping("authorization")
    public String authorizationGetMethod(Model model){
        model.addAttribute("student", new User());
        return "authorization";
    }

    @PostMapping("authorization")
    public String authorizationPostMethod(
            @ModelAttribute("student") User user,
            HttpSession session){
        User user1 = service.getUserByLoginAndPassword(user.getLogin(),
                user.getPassword());
        if(Objects.isNull(user1)) {
            return "redirect:authorization";
        }
        session.setAttribute("user", user1);
        if(user1.getRole().equals(Role.TEACHER)){
            return "redirect:teacher/courses";
        }
        return "redirect:student/mainPage";
    }

}
