package com.example.diploma.enteties;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "course")
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(
            name = "course_title",
            nullable = false
    )
    private String courseTitle;

    @Column(
            name="price",
            nullable = false
    )
    private Double price;

    @Column(
            name="teacher_name",
            nullable = false
    )
    private String teacherName;


    public Course() {
    }

    public Course(Long id, String courseTitle, Double price, String teacherName) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.price = price;
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", price=" + price +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
