package com.studentmanagement.services;

import com.studentmanagement.entities.Task;
import com.studentmanagement.repositories.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public Task getTask(int id) {
        return repository.findById(id);
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }
}
