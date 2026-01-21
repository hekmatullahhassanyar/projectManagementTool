package com.studentmanagement.services;

import com.studentmanagement.entities.Course;
import com.studentmanagement.repositories.CourseRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void addCourse(Course course) {
        repository.save(course);
    }

    public Course getCourse(int id) {
        return repository.findById(id);
    }

    public List<Course> listCourses() {
        return repository.findAll();
    }
}
