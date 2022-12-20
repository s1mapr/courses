package com.example.diploma.enteties;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "courseMaterial")
@Table(name="course_material")
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseMaterialId;

    @Column(name="video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

}
