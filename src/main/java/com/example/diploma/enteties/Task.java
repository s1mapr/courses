package com.example.diploma.enteties;

import jakarta.persistence.*;

@Entity(name="task")
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="description",
            nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_material_id", referencedColumnName = "id")
    private CourseMaterial courseMaterial;
}
