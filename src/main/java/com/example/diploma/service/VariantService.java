package com.example.diploma.service;

import com.example.diploma.enteties.Task;
import com.example.diploma.enteties.Variant;
import com.example.diploma.repositories.VariantRepository;
import org.aspectj.weaver.ast.Var;
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
}
