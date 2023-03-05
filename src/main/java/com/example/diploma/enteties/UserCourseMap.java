package com.example.diploma.enteties;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name="userСourse")
@Table(name="user_course")
public class UserCourseMap {

    @EmbeddedId
    private UserCoursePK pk;
    @Column(name="status")
    private Boolean status;

    public UserCourseMap() {
    }


    public UserCourseMap(UserCoursePK pk, Boolean status) {
        this.pk = pk;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
