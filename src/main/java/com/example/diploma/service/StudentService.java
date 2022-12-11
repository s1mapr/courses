package com.example.diploma.service;


import com.example.diploma.enteties.Student;
import com.example.diploma.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveUser(Student student){
        studentRepository.save(student);
    }
}
