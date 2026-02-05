package com.studentmanagement.repositories;

import com.studentmanagement.entities.Student;
import com.studentmanagement.exceptions.ConflictException;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;

import java.util.List;

public interface StudentRepository {
    void addStudent(Student student) throws InvalidInputException, ConflictException;

    Student findStudentById(int id) throws NotFoundException;

    void save(Student student);
    Student findById(int id);
    List<Student> findAll();
}
