package com.prokopenko.diploma.dto;

import com.prokopenko.diploma.entities.Course;
import com.prokopenko.diploma.entities.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class UserCoursePK implements Serializable {
    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserCoursePK(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    public UserCoursePK() {
    }
}
