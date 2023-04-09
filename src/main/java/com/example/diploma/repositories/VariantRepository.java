package com.example.diploma.repositories;

import com.example.diploma.enteties.CourseMaterial;import com.example.diploma.enteties.Task;
import com.example.diploma.enteties.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {

    List<Variant> getVariantByTask(Task task);

    List<Variant> getVariantByTaskId(Long id);

    void deleteAllByTaskCourseMaterial(CourseMaterial courseMaterial);
}
