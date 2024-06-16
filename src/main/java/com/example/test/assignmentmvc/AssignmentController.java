package com.example.test.assignmentmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assignmnet")
public class AssignmentController {
    @Autowired
    private final AssignmentRepository assignmentRepository;

    public AssignmentController(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping("")
    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    @PostMapping("")
    public void create(@RequestBody Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Assignment assignment, @RequestParam Long id) {
        assignmentRepository.save(assignment);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        assignmentRepository.delete(assignmentRepository.findById(id).get());
    }
}
