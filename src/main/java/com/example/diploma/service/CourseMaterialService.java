package com.example.diploma.service;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.CourseMaterial;
import com.example.diploma.repositories.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMaterialService {

    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    public void setCourseMaterialRepository(CourseMaterialRepository courseMaterialRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
    }

    public void saveCourseMaterial(CourseMaterial courseMaterial){
        courseMaterialRepository.save(courseMaterial);
    }

    public CourseMaterial getCourseMaterialByCourseMaterialId(Long id){
        return courseMaterialRepository.getCourseMaterialByCourseMaterialId(id);
    }


    public List<CourseMaterial> getCourseMaterialsByCourse(Course course){
        return courseMaterialRepository.getCourseMaterialsByCourse(course);
    }

}
