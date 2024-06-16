package com.example.test.tasksmvc;

import com.example.test.assignmentmvc.Assignment;
import com.example.test.submissionmvc.Submission;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskDescription;
    private Integer taskOutOf;
    private String testCase;

    // the reverse for the assignment
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @OneToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;

    public Submission getSubmission() {
        return submission;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Long getId() {
        return id;
    }
    
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getTaskOutOf() {
        return taskOutOf;
    }

    public void setTaskOutOf(Integer taskOutOf) {
        this.taskOutOf = taskOutOf;
    }

    public String getTestCase() {
        return testCase;
    }
    
    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public Task(String taskDescription, Integer taskOutOf, String testCase) {
        this.taskDescription = taskDescription;
        this.taskOutOf = taskOutOf;
        this.testCase = testCase;
    }
}
