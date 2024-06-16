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

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @PostMapping("")
    public void create(@RequestBody Course course) {
        courseRepository.save(course);
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
