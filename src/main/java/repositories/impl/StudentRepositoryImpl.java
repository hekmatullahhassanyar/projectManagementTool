package com.studentmanagement.repositories.impl;

import com.studentmanagement.database.IDatabase;
import com.studentmanagement.entities.Student;
import com.studentmanagement.exceptions.ConflictException;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final IDatabase database;

    public StudentRepositoryImpl(IDatabase database) {
        this.database = database;
    }

    @Override
    public void addStudent(Student student) throws InvalidInputException, ConflictException {
        if (student.getName() == null || student.getEmail() == null) {
            throw new InvalidInputException("Student name or email cannot be null");
        }

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO students (id, name, email) VALUES (?, ?, ?)")) {

            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) { // unique violation
                throw new ConflictException("Student with this ID or email already exists");
            }
            throw new RuntimeException("Database error while adding student", e);
        }
    }

    @Override
    public Student findStudentById(int id) throws NotFoundException {
        return null;
    }

    @Override
    public Student findById(int id) throws NotFoundException {
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, name, email FROM students WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            } else {
                throw new NotFoundException("Student not found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error while finding student", e);
        }
    }

    @Override
    public void save(Student student) throws InvalidInputException, NotFoundException {
        if (student.getName() == null || student.getEmail() == null) {
            throw new InvalidInputException("Student name or email cannot be null");
        }

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE students SET name = ?, email = ? WHERE id = ?")) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new NotFoundException("No student found with ID: " + student.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error while saving student", e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, name, email FROM students");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error while retrieving all students", e);
        }

        return students;
    }
}
