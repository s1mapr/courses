package com.example.diploma.enteties;

import jakarta.persistence.*;
import java.time.LocalDate;


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


    @Column(name="description")
    private String description;

    @Column(name="intro_url")
    private String introUrl;

    @Column(
            name="picture_url",
            columnDefinition = "TEXT(1000)")
    private String pictureUrl;

    @Column(name="complexity")
    private String complexity;

    @Column(name="date")
    private LocalDate date = LocalDate.now();

    @Column(name="state",
    columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private CourseStatus state = CourseStatus.PLANNED;


    public Course() {
    }

    public Course(Long id, String courseTitle, Double price, String teacherName, String description, String introUrl, String pictureUrl) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.price = price;
        this.teacherName = teacherName;
        this.description = description;
        this.introUrl = introUrl;
        this.pictureUrl = pictureUrl;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroUrl() {
        return introUrl;
    }

    public void setIntroUrl(String introUrl) {
        this.introUrl = introUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public CourseStatus getState() {
        return state;
    }

    public void setState(CourseStatus state) {
        this.state = state;
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
