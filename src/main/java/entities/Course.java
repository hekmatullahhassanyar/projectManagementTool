package com.studentmanagement.entities;

public class Course {
    private int id;
    private String name;

    public Course(int id, String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}
