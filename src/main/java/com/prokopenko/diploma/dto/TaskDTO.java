package com.prokopenko.diploma.dto;

import com.prokopenko.diploma.enteties.Task;
import com.prokopenko.diploma.enteties.Variant;

import java.util.List;

public class TaskDTO {
    private Task task;
    private List<Variant> variants;

    public TaskDTO() {
    }

    public TaskDTO(Task task, List<Variant> variants) {
        this.task = task;
        this.variants = variants;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
