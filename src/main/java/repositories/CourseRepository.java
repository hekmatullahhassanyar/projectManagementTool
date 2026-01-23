package com.studentmanagement.repositories;

import com.studentmanagement.entities.Course;
import java.util.List;

public interface CourseRepository {
    void save(Course course);
    Course findById(int id);
    List<Course> findAll();
}
