package com.example.diploma.controllers;

import com.example.diploma.enteties.Role;
import com.example.diploma.enteties.User;
import com.example.diploma.service.UserCourseMapService;
import com.example.diploma.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    private UserService userService;

    private UserCourseMapService userCourseMapService;

    @Autowired
    public void setUserCourseMapService(UserCourseMapService userCourseMapService) {
        this.userCourseMapService = userCourseMapService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String getMainPage(){
        return "redirect:authorization";
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
        user.setDate(LocalDate.now());
        userService.saveUser(user);
        return "redirect:authorization";
    }

    @GetMapping("/authorization")
    public String authorizationGetMethod(Model model){
        model.addAttribute("student", new User());
        return "authorization";
    }

    @PostMapping("authorization")
    public String authorizationPostMethod(
            @ModelAttribute("student") User user,
            HttpSession session){
        User user1 = userService.getUserByLoginAndPassword(user.getLogin(),
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

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "authorization";
    }

}
