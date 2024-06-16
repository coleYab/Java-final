package com.example.test.usermvc;

import java.util.ArrayList;
import java.util.List;

import com.example.test.coursemvc.Course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Teacher extends User {
    // add courses that you need to create
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Teacher(String fullName, String userName, String password) {
        super(fullName, userName, password);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addNewCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
        }
    }
}
