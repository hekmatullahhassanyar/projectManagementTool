package com.studentmanagement.services;

import com.studentmanagement.entities.Task;
import com.studentmanagement.filters.TaskFilter;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.TaskRepository;
import java.util.ArrayList;

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
        return repository.findById(id);
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }

    public List<Task> filterTasks(TaskFilter<Task> filter) {

        List<Task> result = new ArrayList<>();

        for (Task task : repository.findAll()) {
            if (filter.filter(task)) {
                result.add(task);
            }
        }

        return result;
    }

    public List<Task> getOverdueTasks() {
        List<Task> result = new ArrayList<>();
        for (Task task : repository.findAll()) {
            if (task.getDueDate().isBefore(java.time.LocalDate.now())) {
                result.add(task);
            }
        }
        return result;
    }

    public void changeTaskStatus(int id, String newStatus) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new NotFoundException("Task not found with id: " + id);
        }

        if (newStatus == null || newStatus.isBlank()) {
            throw new InvalidInputException("Status cannot be empty");
        }

        task.changeStatus(newStatus);   // ВАЖНО: Task должен иметь setStatus(String)
        repository.save(task);       // если save обновляет существующую задачу
    }


}
