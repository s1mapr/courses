package com.example.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiplomaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaApplication.class, args);
    }

//@Bean
//    CommandLineRunner commandLineRunner (UserRepository studentRepository){
//        return args -> {
//            User maria = new User("Maria", "Jones", "email@gmail.com", 12);
//            studentRepository.save(maria);
//        };
//    }
}
