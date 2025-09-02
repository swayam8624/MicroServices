package com.example.coursecommon.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseDTO {
    
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotNull
    private Integer credits;
    
    private boolean optedIn;
    
    // Constructors
    public CourseDTO() {}
    
    public CourseDTO(String name, Integer credits) {
        this.name = name;
        this.credits = credits;
    }
    
    public CourseDTO(Long id, String name, Integer credits, boolean optedIn) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.optedIn = optedIn;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCredits() {
        return credits;
    }
    
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    
    public boolean isOptedIn() {
        return optedIn;
    }
    
    public void setOptedIn(boolean optedIn) {
        this.optedIn = optedIn;
    }
}