package com.prokopenko.diploma.repositories;

import com.prokopenko.diploma.enteties.CourseMaterial;
import com.prokopenko.diploma.enteties.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getTasksByCourseMaterial(CourseMaterial courseMaterial);

    Task getTasksById(Long id);

    void deleteAllByCourseMaterial(CourseMaterial courseMaterial);

}
