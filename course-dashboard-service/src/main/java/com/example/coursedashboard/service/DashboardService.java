package com.example.coursedashboard.service;

import com.example.coursedashboard.client.CourseAddClient;
import com.example.coursedashboard.client.CourseDropClient;
import com.example.coursecommon.entity.Course;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CircuitBreaker(name = "dashboardService")
public class DashboardService {

    @Autowired
    private CourseAddClient courseAddClient;

    @Autowired
    private CourseDropClient courseDropClient;

    public DashboardSummary getDashboardSummary() {
        List<Course> availableCourses = courseAddClient.getAvailableCourses();
        List<Course> optedCourses = courseDropClient.getOptedCourses();

        int totalCredits = optedCourses.stream()
                .mapToInt(Course::getCredits)
                .sum();

        return new DashboardSummary(availableCourses, optedCourses, totalCredits);
    }

    public String addCourse(Long id) {
        // In a real implementation, this would call the course-add-service
        // For now, we'll simulate the behavior
        return "Course added successfully";
    }

    public String dropCourse(Long id) {
        return courseDropClient.dropCourse(id);
    }

    public static class DashboardSummary {
        private List<Course> availableCourses;
        private List<Course> optedCourses;
        private int totalCredits;

        public DashboardSummary(List<Course> availableCourses, List<Course> optedCourses, int totalCredits) {
            this.availableCourses = availableCourses;
            this.optedCourses = optedCourses;
            this.totalCredits = totalCredits;
        }

        // Getters and setters
        public List<Course> getAvailableCourses() {
            return availableCourses;
        }

        public void setAvailableCourses(List<Course> availableCourses) {
            this.availableCourses = availableCourses;
        }

        public List<Course> getOptedCourses() {
            return optedCourses;
        }

        public void setOptedCourses(List<Course> optedCourses) {
            this.optedCourses = optedCourses;
        }

        public int getTotalCredits() {
            return totalCredits;
        }

        public void setTotalCredits(int totalCredits) {
            this.totalCredits = totalCredits;
        }
    }
}