package com.studentmanagement.repositories.impl;

import com.studentmanagement.database.IDatabase;
import com.studentmanagement.entities.Submission;
import com.studentmanagement.repositories.SubmissionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubmissionRepositoryImpl implements SubmissionRepository {
    private final IDatabase database;

    public SubmissionRepositoryImpl(IDatabase database) {
        this.database = database;
    }

    @Override
    public void save(Submission submission) {
        String sql = "INSERT INTO submissions(id, student_id, task_id, submitted_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, submission.getId());
            stmt.setInt(2, submission.getStudentId());
            stmt.setInt(3, submission.getTaskId());
            stmt.setTimestamp(4, Timestamp.valueOf(submission.getSubmittedAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving submission", e);
        }
    }

    @Override
    public Submission findById(int id) {
        String sql = "SELECT * FROM submissions WHERE id = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Submission(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("task_id"),
                        rs.getTimestamp("submitted_at").toLocalDateTime()
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding submission", e);
        }
    }

    @Override
    public List<Submission> findAll() {
        List<Submission> submissions = new ArrayList<>();
        String sql = "SELECT * FROM submissions";
        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                submissions.add(new Submission(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("task_id"),
                        rs.getTimestamp("submitted_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving submissions", e);
        }
        return submissions;
    }
}
