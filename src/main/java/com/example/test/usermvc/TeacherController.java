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
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    private final TeacherRepository teacherRepository;
    @Autowired
    private final CourseRepository courseRepository;

    public TeacherController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    @GetMapping("")
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @PostMapping("")
    public void create(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Teacher teacher, @RequestParam Long id) {
        teacherRepository.save(teacher);
    }

    @PostMapping("/{id}/course/{c_id}")
    public void addCourse(@RequestParam Long id, @RequestParam Long c_id) {
        teacherRepository.findById(id).get().addNewCourse(courseRepository.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        teacherRepository.delete(teacherRepository.findById(id).get());
    }
}
