package com.prokopenko.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
