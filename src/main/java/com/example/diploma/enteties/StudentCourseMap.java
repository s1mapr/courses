package com.example.diploma.enteties;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name="studentСourse")
@Table(name="student_course")
public class StudentCourseMap {

    @EmbeddedId
    private StudentCoursePK pk;
    @Column(name="progress")
    private Double progress;

    public StudentCourseMap() {
    }

    public StudentCourseMap(StudentCoursePK pk, Double progress) {
        this.pk = pk;
        this.progress = progress;
    }

    public StudentCoursePK getPk() {
        return pk;
    }

    public void setPk(StudentCoursePK pk) {
        this.pk = pk;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}
