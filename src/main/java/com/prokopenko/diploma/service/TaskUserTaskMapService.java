package com.prokopenko.diploma.service;

import com.prokopenko.diploma.enteties.CourseMaterial;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskUserTaskMapService
{
    private TaskService taskService;

    private UserTaskMapService userTaskMapService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setUserTaskMapService(UserTaskMapService userTaskMapService) {
        this.userTaskMapService = userTaskMapService;
    }


    public void deleteTaskByCourseMaterial(CourseMaterial courseMaterial){
        userTaskMapService.deleteEntriesByTasksCourseMaterial(courseMaterial);
        taskService.deleteTaskByCourseMaterial(courseMaterial);
    }
}
