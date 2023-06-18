package com.prokopenko.diploma.controllers;

import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.entities.User;
import com.prokopenko.diploma.service.UserService;
import jakarta.servlet.http.HttpSession;import jakarta.websocket.server.PathParam;import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;

  @GetMapping("/teachers")
  public String getTeachers(Model model, HttpSession session) {
    User user = (User)session.getAttribute("user");
    model.addAttribute("user", user);
    List<User> teachers = userService.getUsersByRole(Role.TEACHER);
    model.addAttribute("teachers", teachers);
    return "admin/teachers";
  }

  @GetMapping("/teacherManipulation/{id}")
    public String getTeacherById(@PathVariable("id") Long id, @PathParam("action") String action, Model model, HttpSession session){
    User user = (User)session.getAttribute("user");
    model.addAttribute("user", user);
    User teacher = userService.getUserById(id);
    if(action.equals("block")){
      teacher.setStatus(false);
    } else if(action.equals("unblock")){
      teacher.setStatus(true);
    }
    userService.saveUser(teacher);
    return "redirect:/admin/teachers";
  }

  @GetMapping("/createTeacher")
  public String createTeacherGetMapping(Model model, HttpSession session) {
    User user = (User)session.getAttribute("user");
    model.addAttribute("user", user);
    User teacher = new User();
    model.addAttribute("teacher", teacher);
    return "admin/createTeacher";
  }

  @PostMapping("/createTeacher")
  public String createTeacherPostMapping(@ModelAttribute("teacher") User teacher) {
    teacher.setRole(Role.TEACHER);
    teacher.setStatus(true);
    userService.saveUser(teacher);
    return "redirect:teachers";
  }

  @GetMapping("/editTeacher/{id}")
  public String editTeacherGetMethod(Model model, HttpSession session, @PathVariable Long id) {
    User admin = (User)session.getAttribute("user");
    User teacher = userService.getUserById(id);
    model.addAttribute("user", admin);
    model.addAttribute("teacher", teacher);
    return "admin/editTeacher";
  }

  @PostMapping("/editTeacher/{id}")
  public String editTeacherPostMethod(@ModelAttribute("teacher") User teacher){
    userService.saveUser(teacher);
    return "redirect:/admin/teachers";
  }


}
