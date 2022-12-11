package com.example.diploma.repositories;

import com.example.diploma.enteties.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
