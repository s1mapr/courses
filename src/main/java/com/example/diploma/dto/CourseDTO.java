package com.example.diploma.dto;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.CourseMaterial;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Course course;
    private List<CourseMaterial> courseMaterials;

    private Boolean status;

    private Double progress;

    private String ukrValueOfSubject;

    private String ukrValueOfComplexity;

    public CourseDTO(Course course, Double progress, Boolean status){
        this.course = course;
        this.progress = progress;
        this.status = status;
    }

}

