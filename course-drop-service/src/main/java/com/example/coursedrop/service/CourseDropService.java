package com.example.coursedrop.service;

import com.example.coursecommon.entity.Course;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class CourseDropService {

    private List<Course> courses = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with some sample courses
        Course math = new Course("Mathematics", 3);
        math.setOptedIn(true);
        Course physics = new Course("Physics", 4);
        physics.setOptedIn(true);
        courses.add(math);
        courses.add(physics);
    }

    public boolean dropCourse(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id) && course.isOptedIn())
                .findFirst()
                .map(course -> {
                    course.setOptedIn(false);
                    return true;
                })
                .orElse(false);
    }

    public List<Course> getOptedCourses() {
        return courses.stream()
                .filter(Course::isOptedIn)
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