package com.example.diploma.service;

import com.example.diploma.enteties.CourseMaterial;
import com.example.diploma.repositories.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
