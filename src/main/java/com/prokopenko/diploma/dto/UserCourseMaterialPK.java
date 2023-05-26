package com.prokopenko.diploma.dto;

import com.prokopenko.diploma.enteties.CourseMaterial;
import com.prokopenko.diploma.enteties.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class UserCourseMaterialPK implements Serializable {
    @ManyToOne()
    @JoinColumn(name = "material_id")
    private CourseMaterial courseMaterial;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public UserCourseMaterialPK() {
    }

    public UserCourseMaterialPK(CourseMaterial courseMaterial, User user) {
        this.courseMaterial = courseMaterial;
        this.user = user;
    }

    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}