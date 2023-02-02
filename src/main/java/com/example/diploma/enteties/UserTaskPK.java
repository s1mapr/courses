package com.example.diploma.enteties;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;


@Embeddable
public class UserTaskPK implements Serializable {
    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public UserTaskPK() {
    }

    public UserTaskPK(Task task, User user) {
        this.task = task;
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}