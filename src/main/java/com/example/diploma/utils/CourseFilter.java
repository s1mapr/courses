package com.example.diploma.utils;

import com.example.diploma.dto.CourseDTO;
import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.Filter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CourseFilter {

  private List<Course> listOfCourses;

  private List<CourseDTO> listOfCourseDTOS;

  private Filter filter;

  public CourseFilter(List<Course> listOfCourses, Filter filter) {
    this.listOfCourses = listOfCourses;
    this.filter = filter;
  }

  public List<Course> doFilter() {
    filter.setComplexity(editList(filter.getComplexity()));
    filter.setAuthor(editList(filter.getAuthor()));
    filter.setSubject(editList(filter.getSubject()));
    complexityFilter(filter.getComplexity());
    authorFilter(filter.getAuthor());
    nameFilter(filter.getTitle());
    minPriceFilter(filter.getMinPrice());
    maxPriceFilter(filter.getMaxPrice());
    subjectFilter(filter.getSubject());
    return listOfCourses;
  }

  public List<CourseDTO> doFilterForDTO(List<CourseDTO> courseDTOS, Filter filter) {
    this.listOfCourseDTOS = courseDTOS;
    this.filter = filter;
    nameFilterForDTO(filter.getTitle());
    sort(filter.getSortParameter());
    return listOfCourseDTOS;
  }

  private List<String> editList(List<String> list) {
    List<String> temp = null;
    if (Objects.nonNull(list)) {
      temp = new ArrayList<>();
      for (String str : list) {
        if (Objects.isNull(str)) {
          temp.add("");
        } else {
          temp.add(str);
        }
      }
    }
    return temp;
  }

  private void complexityFilter(List<String> complexity) {
    if (Objects.nonNull(complexity)) {
      listOfCourses =
          listOfCourses.stream()
              .filter(x -> (complexity.contains(x.getComplexity().toString().toLowerCase())))
              .collect(Collectors.toList());
    }
  }

  private void authorFilter(List<String> author) {
    if (Objects.nonNull(author)) {
      listOfCourses =
          listOfCourses.stream()
              .filter(x -> (author.contains(x.getTeacherName())))
              .collect(Collectors.toList());
    }
  }

  private void nameFilter(String name) {
    if (Objects.nonNull(name)) {
      listOfCourses =
          listOfCourses.stream()
              .filter(x -> (x.getCourseTitle().toLowerCase().contains(name.toLowerCase())))
              .collect(Collectors.toList());
    }
  }

  private void nameFilterForDTO(String name) {
    if (Objects.nonNull(name)) {
      listOfCourseDTOS =
          listOfCourseDTOS.stream()
              .filter(
                  x -> (x.getCourse().getCourseTitle().toLowerCase().contains(name.toLowerCase())))
              .collect(Collectors.toList());
    }
  }

  private void minPriceFilter(Double minPrice) {
    if (Objects.nonNull(minPrice)) {
      listOfCourses =
          listOfCourses.stream()
              .filter(x -> (x.getPrice() >= minPrice))
              .collect(Collectors.toList());
    }
  }

  private void maxPriceFilter(Double maxPrice) {
    if (Objects.nonNull(maxPrice)) {
      listOfCourses =
          listOfCourses.stream()
              .filter(x -> (x.getPrice() <= maxPrice))
              .collect(Collectors.toList());
    }
  }

  private void subjectFilter(List<String> subjects) {
    if (Objects.nonNull(subjects)) {
      listOfCourses =
          listOfCourses.stream()
              .filter(x -> (subjects.contains(x.getSubject().toString().toLowerCase())))
              .collect(Collectors.toList());
    }
  }

  private void sort(String sortParameter) {
    if (Objects.nonNull(sortParameter)) {
      switch (sortParameter) {
        case "byOldestDate" -> listOfCourseDTOS =
            listOfCourseDTOS.stream()
                .sorted(
                    Comparator.comparing(
                        courseDTO -> courseDTO.getCourse().getDate(), Comparator.naturalOrder()))
                .collect(Collectors.toList());
        case "byNewestDate" -> listOfCourseDTOS =
            listOfCourseDTOS.stream()
                .sorted(
                    Comparator.comparing(
                        courseDTO -> courseDTO.getCourse().getDate(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        case "byLowestProgress" -> listOfCourseDTOS =
            listOfCourseDTOS.stream()
                .sorted(Comparator.comparing(CourseDTO::getProgress, Comparator.naturalOrder()))
                .collect(Collectors.toList());
        case "byHighestProgress" -> listOfCourseDTOS =
            listOfCourseDTOS.stream()
                .sorted(Comparator.comparing(CourseDTO::getProgress, Comparator.reverseOrder()))
                .collect(Collectors.toList());
      }
    }
  }
}
