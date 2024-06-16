package com.example.test.usermvc;

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

import com.example.test.coursemvc.CourseRepository;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @PostMapping("")
    public void create(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PostMapping("/{id}/course/{c_id}")
    public void addCourse(@RequestParam Long id, @RequestParam Long c_id) {
        studentRepository.findById(id).get().addNewCourse(
            courseRepository.findById(c_id).get()
        );
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Student student, @RequestParam Long id) {
        studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        studentRepository.delete(studentRepository.findById(id).get());
    }
}
