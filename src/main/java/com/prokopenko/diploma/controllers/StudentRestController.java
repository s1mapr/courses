package com.prokopenko.diploma.controllers;

import com.prokopenko.diploma.dto.UserTaskPK;
import com.prokopenko.diploma.enteties.Task;import com.prokopenko.diploma.enteties.User;import com.prokopenko.diploma.enteties.UserTaskMap;import com.prokopenko.diploma.enteties.Variant;import com.prokopenko.diploma.service.TaskService;
import com.prokopenko.diploma.service.UserTaskMapService;
import com.prokopenko.diploma.service.VariantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StudentRestController {

    private VariantService variantService;

    private UserTaskMapService userTaskMapService;

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setUserTaskMapService(UserTaskMapService userTaskMapService) {
        this.userTaskMapService = userTaskMapService;
    }

    @Autowired
    public void setVariantService(VariantService variantService) {
        this.variantService = variantService;
    }

    @PostMapping("/student/course/checkAnswers")
    public void checkAnswers(HttpServletRequest request,
                             HttpSession session){
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        User user = (User)session.getAttribute("user");
        Task task = taskService.getTaskById(taskId);
        String studentAnswer = request.getParameter("taskAnswer");
        List<Variant> variants = variantService.getListOfVariants(task);
        String correctAnswer = variants
                .stream()
                .map(Variant::getStatus)
                .map(x->x?"1":"0")
                .reduce("",(a,b)->a+b);
        boolean status = studentAnswer.equals(correctAnswer);
        userTaskMapService.saveUserTaskMap(new UserTaskMap(new UserTaskPK(task, user),status));
    }
}
