package com.prokopenko.diploma.service;

import com.prokopenko.diploma.enteties.CourseMaterial;import com.prokopenko.diploma.enteties.Task;
import com.prokopenko.diploma.enteties.Variant;
import com.prokopenko.diploma.repositories.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantService {
    private VariantRepository variantRepository;

    @Autowired
    public void setVariantRepository(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public void saveVariant(Variant variant){
        variantRepository.save(variant);
    }

    public List<Variant> getListOfVariants(Task task){
        return variantRepository.getVariantByTask(task);
    }

    public void deleteVariantById(Long id){
        variantRepository.deleteById(id);
    }

    public List<Variant> getListOfVariants(Long id){
        return variantRepository.getVariantByTaskId(id);
    }

    public void deleteAllByTask(CourseMaterial courseMaterial){
        variantRepository.deleteAllByTaskCourseMaterial(courseMaterial);
    };
}
