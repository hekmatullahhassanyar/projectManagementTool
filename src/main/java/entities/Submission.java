package com.studentmanagement.entities;

import java.time.LocalDateTime;

public class Submission {
    private int id;
    private int studentId;
    private int taskId;
    private LocalDateTime submittedAt;

    public Submission(int id, int studentId, int taskId, LocalDateTime submittedAt) {
        this.id = id;
        this.studentId = studentId;
        this.taskId = taskId;
        this.submittedAt = submittedAt;
    }

    // Getters
    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getTaskId() { return taskId; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
}
