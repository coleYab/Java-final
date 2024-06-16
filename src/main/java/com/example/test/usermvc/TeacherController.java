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

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
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

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        teacherRepository.delete(teacherRepository.findById(id).get());
    }
}
