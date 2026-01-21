package com.studentmanagement.repositories;

import com.studentmanagement.entities.Task;
import java.util.List;

public interface TaskRepository {
    void save(Task task);
    Task findById(int id);
    List<Task> findAll();
}
