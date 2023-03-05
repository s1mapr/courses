package com.example.diploma.utils;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseFilter {

    private static List<Course> listOfCourses;

    public static List<Course> doFilter(List<Course> courses, Filter filter, String count){
        listOfCourses = courses;
        filter.setComplexity(editList(filter.getComplexity()));
        filter.setAuthor(editList(filter.getAuthor()));
        complexityFilter(filter.getComplexity());
        authorFilter(filter.getAuthor());
        nameFilter(filter.getTitle());
        minPriceFilter(filter.getMinPrice());
        maxPriceFilter(filter.getMaxPrice());
        return listOfCourses;
    }

    private static List<String> editList(List<String> list){
        List<String> temp = null;
        if(Objects.nonNull(list)){
            temp = new ArrayList<>();
            for(String str : list){
                if(Objects.isNull(str)){
                    temp.add("");
                }else{
                    temp.add(str);
                }
            }
        }
        return temp;
    }


    private static void complexityFilter(List<String> complexity){
        if(Objects.nonNull(complexity)){
            listOfCourses = listOfCourses
                    .stream()
                    .filter(x->(complexity.contains(x.getComplexity())))
                    .collect(Collectors.toList());
        }
    }


    private static void authorFilter(List<String> author){
        if(Objects.nonNull(author)){
            listOfCourses = listOfCourses
                    .stream()
                    .filter(x->(author.contains(x.getTeacherName())))
                    .collect(Collectors.toList());
        }
    }

    private static void nameFilter(String name){
        if(Objects.nonNull(name)){
            listOfCourses = listOfCourses
                    .stream()
                    .filter(x->(x.getCourseTitle().toLowerCase().contains(name.toLowerCase())))
                    .collect(Collectors.toList());
        }
    }


    private static void minPriceFilter(Double minPrice){
        if(Objects.nonNull(minPrice)){
            listOfCourses = listOfCourses.stream()
                    .filter(x->(x.getPrice()>=minPrice))
                    .collect(Collectors.toList());
        }
    }

    private static void maxPriceFilter(Double maxPrice){
        if(Objects.nonNull(maxPrice)){
            listOfCourses = listOfCourses.stream()
                    .filter(x->(x.getPrice()<=maxPrice))
                    .collect(Collectors.toList());
        }
    }

}
