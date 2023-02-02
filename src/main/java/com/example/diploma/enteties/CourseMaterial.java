package com.example.diploma.enteties;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity(name = "courseMaterial")
@Table(name="course_material")
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long courseMaterialId;

    @Column(name="video_url")
    private String videoUrl;

    @Column(name="document_url")
    private String documentUrl;

    @Column(name="material_name")
    private String materialName;


    @Column(name="description")
    private String description;


    @ManyToOne()
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    public CourseMaterial() {
    }

    public CourseMaterial(Long courseMaterialId, String videoUrl, String documentUrl, String materialName, String description, Course course) {
        this.courseMaterialId = courseMaterialId;
        this.videoUrl = videoUrl;
        this.documentUrl = documentUrl;
        this.materialName = materialName;
        this.description = description;
        this.course = course;
    }

    public Long getCourseMaterialId() {
        return courseMaterialId;
    }

    public void setCourseMaterialId(Long courseMaterialId) {
        this.courseMaterialId = courseMaterialId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
