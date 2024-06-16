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

import com.example.test.tasksmvc.TaskRepository;

@RestController
@RequestMapping("/api/v1/assignmnet")
public class AssignmentController {
    @Autowired
    private final AssignmentRepository assignmentRepository;
    @Autowired
    private final TaskRepository taskRepository;

    public AssignmentController(AssignmentRepository assignmentRepository, TaskRepository taskRepository) {
        this.assignmentRepository = assignmentRepository;
        this.taskRepository = taskRepository;
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

    @PostMapping("/{id}/task/{i_id}")
    public void addAssignment(@RequestParam Long id, @RequestParam Long t_id) {
        assignmentRepository.findById(id).get().addNewTask(
            taskRepository.findById(t_id).get()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        assignmentRepository.delete(assignmentRepository.findById(id).get());
    }
}
