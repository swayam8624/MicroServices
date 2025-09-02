package com.example.courseadd.controller;

import com.example.courseadd.service.CourseAddService;
import com.example.coursecommon.dto.CourseDTO;
import com.example.coursecommon.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseAddController {

    @Autowired
    private CourseAddService courseAddService;

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDTO courseDTO) {
        Course course = courseAddService.addCourse(courseDTO);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Course>> getAvailableCourses() {
        List<Course> courses = courseAddService.getAvailableCourses();
        return ResponseEntity.ok(courses);
    }
}