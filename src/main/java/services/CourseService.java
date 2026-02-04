package com.studentmanagement.services;

import com.studentmanagement.entities.Course;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.CourseRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void addCourse(Course course) {
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new InvalidInputException("Course name cannot be empty");
        }
        repository.save(course);
    }

    public Course getCourse(int id) {
        Course course = repository.findById(id);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        return course;
    }

    public List<Course> listCourses() {
        return repository.findAll();
    }
}
