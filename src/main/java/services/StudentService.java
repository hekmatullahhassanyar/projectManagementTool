package com.studentmanagement.services;

import com.studentmanagement.entities.Student;
import com.studentmanagement.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public Student getStudent(int id) {
        return repository.findById(id);
    }

    public List<Student> listStudents() {
        return repository.findAll();
    }
}
