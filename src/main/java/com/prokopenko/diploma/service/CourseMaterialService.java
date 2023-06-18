package com.prokopenko.diploma.service;

import com.prokopenko.diploma.entities.Course;
import com.prokopenko.diploma.entities.CourseMaterial;
import com.prokopenko.diploma.repositories.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseMaterialService {

    private CourseMaterialRepository courseMaterialRepository;

    private UserMaterialMapService userMaterialMapService;

    private TaskUserTaskMapService taskUserTaskMapService;

    private VariantService variantService;


    @Autowired
    public void setVariantService(VariantService variantService){
        this.variantService = variantService;
    }

    @Autowired
    public void setTaskUserTaskMapService(TaskUserTaskMapService taskUserTaskMapService) {
        this.taskUserTaskMapService = taskUserTaskMapService;
    }
    @Autowired
    public void setUserMaterialMapService(UserMaterialMapService userMaterialMapService) {
        this.userMaterialMapService = userMaterialMapService;
    }
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

    @Transactional
    public void deleteCourseMaterial(CourseMaterial courseMaterial){
        variantService.deleteAllByTask(courseMaterial);
        taskUserTaskMapService.deleteTaskByCourseMaterial(courseMaterial);
        userMaterialMapService.deleteByCourseMaterial(courseMaterial);
        courseMaterialRepository.delete(courseMaterial);
    }
}
