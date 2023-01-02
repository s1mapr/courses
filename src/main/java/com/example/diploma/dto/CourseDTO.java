package com.example.diploma.dto;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.CourseMaterial;

import java.util.List;

public class CourseDTO {

    private Course course;
    private List<CourseMaterial> courseMaterials;


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<CourseMaterial> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<CourseMaterial> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }
}

