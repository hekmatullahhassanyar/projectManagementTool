package com.studentmanagement.entities;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status; // NEW: status field

    public Task(int id, String title, String description, LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be in the past");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = "TODO"; // default status
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }

    public void changeStatus(String newStatus) {
        if (!newStatus.equals("TODO") && !newStatus.equals("IN_PROGRESS") && !newStatus.equals("DONE")) {
            throw new IllegalArgumentException("Invalid status transition");
        }
        this.status = newStatus;
    }
}
