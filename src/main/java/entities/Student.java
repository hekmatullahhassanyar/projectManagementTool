package com.studentmanagement.entities;

public class Student {
    private int id;
    private String name;
    private String email;

    public Student(int id, String name, String email) {
        if (name == null || email == null || name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Name and email cannot be null or empty");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
