package com.prokopenko.diploma.service;

import com.prokopenko.diploma.dto.CourseStatus;
import com.prokopenko.diploma.dto.Role;
import com.prokopenko.diploma.enteties.UserCourseMaterialMap;import com.prokopenko.diploma.repositories.UserCourseMapRepository;
import com.prokopenko.diploma.repositories.UserMaterialMapRepository;
import com.prokopenko.diploma.enteties.Course;import com.prokopenko.diploma.enteties.User;import com.prokopenko.diploma.enteties.UserCourseMap;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseMapService {

    private UserCourseMapRepository userCourseMapRepository;

    private UserMaterialMapRepository userMaterialMapRepository;

    @Autowired
    public void setUserMaterialMapRepository(UserMaterialMapRepository userMaterialMapRepository) {
        this.userMaterialMapRepository = userMaterialMapRepository;
    }

    @Autowired
    public void setUserCourseMapRepository(UserCourseMapRepository userCourseMapRepository) {
        this.userCourseMapRepository = userCourseMapRepository;
    }

    public void saveUserCourseMap(UserCourseMap userCourseMap) {
        userCourseMapRepository.save(userCourseMap);
    }


    public List<UserCourseMap> getListOfUserCourseMapsByCourse(Course course) {

        return userCourseMapRepository.findUserCourseMapByPk_Course(course);
    }

    public List<UserCourseMap> getListOfUserCourseMapsByUserForTeacher(User user) {
    List<UserCourseMap> userCourseMapsWithStartedState =
        userCourseMapRepository.findUserCourseMapByPk_UserAndPkCourseState(
            user, CourseStatus.STARTED);
        List<UserCourseMap> userCourseMapsWithPlannedState = userCourseMapRepository
                .findUserCourseMapByPk_UserAndPkCourseState(user, CourseStatus.PLANNED);
        userCourseMapsWithPlannedState.addAll(userCourseMapsWithStartedState);
        return userCourseMapsWithPlannedState;
    }

    public List<UserCourseMap> getListOfUserCourseMapsByUserForStudent(User user) {
        List<UserCourseMap> userCourseMapsWithStartedState = userCourseMapRepository
                .findUserCourseMapByPk_UserAndPkCourseState(user, CourseStatus.STARTED);
        List<UserCourseMap> userCourseMapsWithDeletedState = userCourseMapRepository
                .findUserCourseMapByPk_UserAndPkCourseState(user, CourseStatus.DELETED);
        userCourseMapsWithDeletedState.addAll(userCourseMapsWithStartedState);
        return userCourseMapsWithDeletedState;
    }

    public boolean checkUserCourseMapIfExist(User user, Course course) {
        return userCourseMapRepository.existsUserCourseMapByPk_UserAndPk_Course(user, course);
    }

    public List<UserCourseMap> getListOfUserCourseMapsByCourseAndRole(Course course, Role role){
        return userCourseMapRepository.findUserCourseMapByPkCourseAndPkUserRole(course, role);
    }

    public void updateCourseStatusIfNeeded(Course course, User user){
        List<UserCourseMaterialMap> userMaterialList = userMaterialMapRepository
                .findUserCourseMaterialMapByPkUserAndPkCourseMaterialCourse(user, course);
        if(!userMaterialList.isEmpty()) {
            int countOfCompletedMaterials = userMaterialList.stream()
                    .map(UserCourseMaterialMap::getStatus)
                    .mapToInt(b -> b ? 1 : 0)
                    .sum();
            UserCourseMap userCourse = userCourseMapRepository.findUserCourseMapByPk_CourseAndPk_User(course, user);
            if (!userCourse.getStatus() && countOfCompletedMaterials == userMaterialList.size()) {
                userCourse.setStatus(true);
                saveUserCourseMap(userCourse);
            }
        }
    }

    public UserCourseMap getUserCourseByCourseAndUser(Course course, User user){
        return userCourseMapRepository.findUserCourseMapByPk_CourseAndPk_User(course, user);
    }



}
