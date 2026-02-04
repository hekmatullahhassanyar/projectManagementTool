package com.studentmanagement.services;

import com.studentmanagement.entities.Task;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        if (task.getDueDate().isBefore(LocalDate.now())) {
            throw new InvalidInputException("Deadline cannot be in the past");
        }
        repository.save(task);
    }

    public Task getTask(int id) {
        Task task = repository.findById(id);
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        return task;
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }

    public void changeTaskStatus(int id, String newStatus) {
        Task task = getTask(id);
        task.changeStatus(newStatus); // may throw IllegalArgumentException
        // optionally update DB here if needed
    }
}
