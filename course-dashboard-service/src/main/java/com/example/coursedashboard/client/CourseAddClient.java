package com.example.coursedashboard.client;

import com.example.coursecommon.entity.Course;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "course-add-service")
@CircuitBreaker(name = "courseAddClient", fallbackMethod = "getDefaultAvailableCourses")
public interface CourseAddClient {

    @GetMapping("/api/courses/available")
    List<Course> getAvailableCourses();

    default List<Course> getDefaultAvailableCourses(Exception ex) {
        return List.of();
    }
}