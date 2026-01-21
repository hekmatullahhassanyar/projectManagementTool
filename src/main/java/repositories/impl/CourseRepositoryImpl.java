package com.studentmanagement.repositories.impl;

import com.studentmanagement.database.IDatabase;
import com.studentmanagement.entities.Course;
import com.studentmanagement.repositories.CourseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {
    private final IDatabase database;

    public CourseRepositoryImpl(IDatabase database) {
        this.database = database;
    }

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses(id, name) VALUES (?, ?)";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, course.getId());
            stmt.setString(2, course.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving course", e);
        }
    }

    @Override
    public Course findById(int id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(rs.getInt("id"), rs.getString("name"));
            }
            return null; // or throw NotFoundException if you prefer
        } catch (SQLException e) {
            throw new RuntimeException("Error finding course", e);
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving courses", e);
        }
        return courses;
    }
}
