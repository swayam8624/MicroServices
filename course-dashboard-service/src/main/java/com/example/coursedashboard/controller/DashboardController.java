package com.example.coursedashboard.controller;

import com.example.coursedashboard.service.DashboardService;
import com.example.coursecommon.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardService.DashboardSummary> getDashboardSummary() {
        DashboardService.DashboardSummary summary = dashboardService.getDashboardSummary();
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/courses/add/{id}")
    public ResponseEntity<String> addCourse(@PathVariable Long id) {
        String result = dashboardService.addCourse(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/courses/drop/{id}")
    public ResponseEntity<String> dropCourse(@PathVariable Long id) {
        String result = dashboardService.dropCourse(id);
        return ResponseEntity.ok(result);
    }
}