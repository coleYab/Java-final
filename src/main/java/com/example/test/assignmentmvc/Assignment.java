package com.example.test.assignmentmvc;

import java.util.*;

import com.example.test.coursemvc.Course;
import com.example.test.tasksmvc.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Integer outOf;

    // we need reverse for the assignment
    @ManyToOne
    @JoinColumn(name = "course_id")    
    private Course course;

    // we need mapping here to all the taks
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Long getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getOutOf() {
        return outOf;
    }
    
    public void setOutOf(Integer outOf) {
        this.outOf = outOf;
    }

    public Assignment(String description, Integer outOf) {
        this.description = description;
        this.outOf = outOf;
    }

    public Course getCourse() {
        return course;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
