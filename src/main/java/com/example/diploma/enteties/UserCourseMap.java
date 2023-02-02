package com.example.diploma.enteties;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="userСourse")
@Table(name="user_course")
public class UserCourseMap {

    @EmbeddedId
    private UserCoursePK pk;
    @Column(name="progress")
    private Double progress;

    public UserCourseMap() {
    }


    public UserCourseMap(UserCoursePK pk, Double progress) {
        this.pk = pk;
        this.progress = progress;
    }

    public UserCourseMap(UserCoursePK pk) {
        this.pk = pk;
    }

    public UserCoursePK getPk() {
        return pk;
    }

    public void setPk(UserCoursePK pk) {
        this.pk = pk;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }


}
