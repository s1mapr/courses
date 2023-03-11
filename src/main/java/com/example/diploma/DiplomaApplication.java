package com.example.diploma;

import com.example.diploma.enteties.UserCourseMap;
import com.example.diploma.repositories.UserCourseMapRepository;
import com.example.diploma.repositories.UserRepository;
import com.example.diploma.service.UserCourseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DiplomaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaApplication.class, args);
    }
//
//
//    private  UserCourseMapRepository userCourseMapService;
//    @Autowired
//    public void setUserCourseMapService(UserCourseMapRepository userCourseMapService) {
//
//        this.userCourseMapService = userCourseMapService;
//    }
//
//
//@Bean
//CommandLineRunner commandLineRunner (UserRepository studentRepository){
//        return args -> {
//            List<UserCourseMap> list = userCourseMapService.findAll();
//            for(UserCourseMap ucm : list){
//                ucm.setStatus(false);
//                userCourseMapService.save(ucm);
//            }
//        };
//    }
}
