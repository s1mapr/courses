package com.prokopenko.diploma.controllers;

import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.enteties.User;
import com.prokopenko.diploma.service.UserService;
import jakarta.websocket.server.PathParam;import lombok.RequiredArgsConstructor;
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
  public String getTeachers(Model model) {
    List<User> teachers = userService.getUsersByRole(Role.TEACHER);
    model.addAttribute("teachers", teachers);
    return "admin/teachers";
  }

  @GetMapping("/teacherManipulation/{id}?action")
  public String getTeacherById(@PathVariable("id") Long id, @PathParam("action") String action){
    User teacher = userService.getUserById(id);
    if(action.equals("block")){
      teacher.setStatus(false);
    } else if(action.equals("unblock")){
      teacher.setStatus(true);
    }
    return "redirect:/teachers";
  }

  @GetMapping("/createTeacher")
  public String createTeacherGetMapping(Model model) {
    User teacher = new User();
    model.addAttribute("teacher", teacher);
    return "admin/createTeacher";
  }

  @PostMapping("/createTeacher")
  public String createTeacherPostMapping(@ModelAttribute("teacher") User teacher) {
    teacher.setRole(Role.TEACHER);
    userService.saveUser(teacher);
    return "redirect:teachers";
  }



}
