package com.studentmanagement.entities;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final int id;
    private final String name;
    private final List<Student> members;
    private final List<Task> tasks;
    private final List<String> tags;

    private Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.members = builder.members;
        this.tasks = builder.tasks;
        this.tags = builder.tags;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Student> getMembers() { return members; }
    public List<Task> getTasks() { return tasks; }
    public List<String> getTags() { return tags; }

    @Override
    public String toString() {
        return "Project{id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members.size() +
                ", tasks=" + tasks.size() +
                ", tags=" + tags + '}';
    }

    public static class Builder {
        private int id;
        private String name;
        private List<Student> members = new ArrayList<>();
        private List<Task> tasks = new ArrayList<>();
        private List<String> tags = new ArrayList<>();

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder addMember(Student student) {
            this.members.add(student);
            return this;
        }

        public Builder addTask(Task task) {
            this.tasks.add(task);
            return this;
        }

        public Builder addTag(String tag) {
            this.tags.add(tag);
            return this;
        }

        public Project build() {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Project name is required");
            }
            return new Project(this);
        }
    }
}
