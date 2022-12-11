package com.example.diploma;

import com.example.diploma.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiplomaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaApplication.class, args);
    }

//@Bean
//    CommandLineRunner commandLineRunner (StudentRepository studentRepository){
//        return args -> {
//            Student maria = new Student("Maria", "Jones", "email@gmail.com", 12);
//            studentRepository.save(maria);
//        };
//    }
}
