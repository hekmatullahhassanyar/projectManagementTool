package com.studentmanagement.services;

import com.studentmanagement.entities.Student;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        if (student.getName() == null || student.getName().isEmpty() ||
                student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new InvalidInputException("Student name and email cannot be empty");
        }
        repository.save(student);
    }

    public Student getStudent(int id) {
        Student student = repository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student not found");
        }
        return student;
    }

    public List<Student> listStudents() {
        return repository.findAll();
    }
}
