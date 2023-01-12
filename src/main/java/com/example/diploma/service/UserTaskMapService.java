package com.example.diploma.service;

import com.example.diploma.enteties.*;
import com.example.diploma.repositories.UserTaskMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTaskMapService {

    private UserTaskMapRepository userTaskMapRepository;

    private TaskService taskService;


    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setUserTaskMapRepository(UserTaskMapRepository userTaskMapRepository) {
        this.userTaskMapRepository = userTaskMapRepository;
    }

    public void saveUserTaskMap(UserTaskMap userTaskMap) {
        userTaskMapRepository.save(userTaskMap);
    }

    public void addTasksForUser(CourseMaterial material, User user) {
        List<Task> tasks = taskService.getMaterialTasks(material);
        for (Task task : tasks) {
            saveUserTaskMap(new UserTaskMap(new UserTaskPK(task, user), false));
        }
    }


}