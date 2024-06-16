package com.example.test.submissionmvc;

import com.example.test.tasksmvc.Task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String sourceCode;
    private Double result;

    @OneToOne(mappedBy = "submission")
    private Task underTask;

    public Long getId() {
        return id;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Submission(String fileName, String sourceCode, Double result) {
        this.fileName = fileName;
        this.sourceCode = sourceCode;
        this.result = result;
    }

    public Task getUnderTask() {
        return underTask;
    }    
}
