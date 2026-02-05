package com.studentmanagement.services;

import com.studentmanagement.entities.Student;
import com.studentmanagement.repositories.StudentRepository;
import com.studentmanagement.notifications.NotificationCenter;


import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.save(student);
        NotificationCenter.getInstance().notifyUser("Student added: " + student.getName());

    }

    public Student getStudent(int id) {
        return repository.findById(id);
    }

    public List<Student> listStudents() {
        return repository.findAll();
    }
}
