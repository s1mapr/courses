package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.entities.CourseMaterial;
import com.prokopenko.diploma.entities.Task;
import com.prokopenko.diploma.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {

    List<Variant> getVariantByTask(Task task);

    List<Variant> getVariantByTaskId(Long id);

    void deleteAllByTaskCourseMaterial(CourseMaterial courseMaterial);
}
