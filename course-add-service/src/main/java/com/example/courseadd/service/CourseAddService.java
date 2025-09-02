package com.example.courseadd.service;

import com.example.coursecommon.dto.CourseDTO;
import com.example.coursecommon.entity.Course;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class CourseAddService {

    private List<Course> courses = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with some sample courses
        courses.add(new Course("Mathematics", 3));
        courses.add(new Course("Physics", 4));
        courses.add(new Course("Chemistry", 3));
        courses.add(new Course("Biology", 4));
        courses.add(new Course("Computer Science", 3));
    }

    public Course addCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO.getName(), courseDTO.getCredits());
        courses.add(course);
        return course;
    }

    public List<Course> getAvailableCourses() {
        return courses.stream()
                .filter(course -> !course.isOptedIn())
                .collect(Collectors.toList());
    }

    public Course getCourseById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateCourseStatus(Long id, boolean optedIn) {
        courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .ifPresent(course -> course.setOptedIn(optedIn));
    }
}