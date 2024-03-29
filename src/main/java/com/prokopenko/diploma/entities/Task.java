package com.prokopenko.diploma.entities;

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

    @Column(name="task_name",
            nullable = false)
    private String taskName;

    @ManyToOne()
    @JoinColumn(name = "course_material_id", referencedColumnName = "id")
    private CourseMaterial courseMaterial;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterial = courseMaterial;
    }
}
