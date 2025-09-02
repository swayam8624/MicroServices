package com.example.coursedrop.controller;

import com.example.coursedrop.service.CourseDropService;
import com.example.coursecommon.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseDropController {

    @Autowired
    private CourseDropService courseDropService;

    @PostMapping("/drop/{id}")
    public ResponseEntity<String> dropCourse(@PathVariable Long id) {
        boolean dropped = courseDropService.dropCourse(id);
        if (dropped) {
            return ResponseEntity.ok("Course dropped successfully");
        } else {
            return ResponseEntity.badRequest().body("Course not found or already dropped");
        }
    }

    @GetMapping("/opted")
    public ResponseEntity<List<Course>> getOptedCourses() {
        List<Course> courses = courseDropService.getOptedCourses();
        return ResponseEntity.ok(courses);
    }
}