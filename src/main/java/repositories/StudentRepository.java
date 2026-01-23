package com.studentmanagement.repositories;

import com.studentmanagement.entities.Student;
import java.util.List;

public interface StudentRepository {
    void save(Student student);
    Student findById(int id);
    List<Student> findAll();
}
