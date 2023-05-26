package com.prokopenko.diploma.service;

import com.prokopenko.diploma.dto.CourseDTO;
import com.prokopenko.diploma.dto.CourseStatus;
import com.prokopenko.diploma.repositories.CourseRepository;
import com.prokopenko.diploma.dto.Complexity;
import com.prokopenko.diploma.dto.Subjects;
import com.prokopenko.diploma.enteties.Course;
import com.prokopenko.diploma.enteties.CourseMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

  private CourseRepository courseRepository;

  private CourseMaterialService courseMaterialService;

  @Autowired
  public void setCourseMaterialService(CourseMaterialService courseMaterialService) {
    this.courseMaterialService = courseMaterialService;
  }

  @Autowired
  public void setCourseRepository(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public void saveCourse(Course course) {
    courseRepository.save(course);
  }

  public CourseDTO getCourseData(Long id) {
    Course course = courseRepository.findCourseById(id);
    List<CourseMaterial> courseMaterialList =
        courseMaterialService.getCourseMaterialsByCourse(course);
    CourseDTO courseInfo = new CourseDTO();
    courseInfo.setUkrValueOfSubject(changeCourseDtoSubjectValue(course));
    courseInfo.setUkrValueOfComplexity(changeCourseDtoComplexityValue(course));
    courseInfo.setCourse(course);
    courseInfo.setCourseMaterials(courseMaterialList);
    return courseInfo;
  }

  public Course getCourseById(Long id) {
    return courseRepository.findCourseById(id);
  }

  public List<Course> findAllStartedCourses() {
    return courseRepository.findAllCoursesByState(CourseStatus.STARTED);
  }

  private String changeCourseDtoSubjectValue(Course course) {
    switch (course.getSubject()) {
      case ENG -> {
        return "Англійська мова";
      }
      case UKR -> {
        return "Українська мова";
      }
      case MATH -> {
        return "Математика";
      }
      case BIOLOGY -> {
        return "Біологія";
      }
      case HISTORY -> {
        return "Історія";
      }
    }
    return "";
  }

  private String changeCourseDtoComplexityValue(Course course) {
    switch (course.getComplexity()) {
      case BEGINNER -> {
        return "Початковий";
      }
      case INTERMEDIATE -> {
        return "Середній";
      }
      case EXPERT -> {
        return "Високий";
      }
    }
    return "";
  }
}
