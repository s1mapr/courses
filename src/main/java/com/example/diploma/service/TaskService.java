package com.example.diploma.service;

import com.example.diploma.enteties.Course;import com.example.diploma.enteties.CourseMaterial;
import com.example.diploma.enteties.Task;
import com.example.diploma.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;


    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getMaterialTasks(CourseMaterial courseMaterial){
        return taskRepository.getTasksByCourseMaterial(courseMaterial);
    }

    public Task getTaskById(Long id){
        return taskRepository.getTasksById(id);
    }


    public void deleteTaskByCourseMaterial(CourseMaterial courseMaterial){
        taskRepository.deleteAllByCourseMaterial(courseMaterial);
    }


}
