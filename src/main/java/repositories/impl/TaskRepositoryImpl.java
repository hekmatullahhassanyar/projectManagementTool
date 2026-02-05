package com.studentmanagement.repositories.impl;


import com.studentmanagement.database.IDatabase;
import com.studentmanagement.entities.Task;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.TaskRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private final IDatabase database;

    public TaskRepositoryImpl(IDatabase database) {
        this.database = database;
    }

    @Override
    public void save(Task task) {
        String sql = "INSERT INTO tasks(id, title, description, due_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setDate(4, Date.valueOf(task.getDueDate()));
            stmt.setString(5, task.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving task", e);
        }
    }

    @Override
    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate()
                );
                task.changeStatus(rs.getString("status"));
                return task;
            }
            throw new NotFoundException("Task with id " + id + " not found");
        } catch (SQLException e) {
            throw new RuntimeException("Error finding task", e);
        }
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate()
                );
                task.changeStatus(rs.getString("status"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving tasks", e);
        }
        return tasks;
    }
}
