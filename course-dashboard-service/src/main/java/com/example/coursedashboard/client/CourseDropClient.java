package com.example.coursedashboard.client;

import com.example.coursecommon.entity.Course;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "course-drop-service")
@CircuitBreaker(name = "courseDropClient", fallbackMethod = "getDefaultOptedCourses")
public interface CourseDropClient {

    @GetMapping("/api/courses/opted")
    List<Course> getOptedCourses();

    @PostMapping("/api/courses/drop/{id}")
    String dropCourse(@PathVariable Long id);

    default List<Course> getDefaultOptedCourses(Exception ex) {
        return List.of();
    }

    default String getDefaultDropCourse(Long id, Exception ex) {
        return "Service unavailable";
    }
}