package com.example.test.coursemvc;

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

import com.example.test.assignmentmvc.AssignmentRepository;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final AssignmentRepository assignmentRepository;

    public CourseController(CourseRepository courseRepository, AssignmentRepository assignmentRepository) {
        this.courseRepository = courseRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping("")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @PostMapping("")
    public void create(@RequestBody Course course) {
        courseRepository.save(course);
    }

    @PostMapping("/{id}/assignment/{a_id}")
    public void addAssignment(@RequestParam Long id, @RequestParam Long a_id) {
        courseRepository.findById(id).get().addNewAssignment(
            assignmentRepository.findById(a_id).get()
        );
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Course course, @RequestParam Long id) {
        courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        courseRepository.delete(courseRepository.findById(id).get());
    }
}
