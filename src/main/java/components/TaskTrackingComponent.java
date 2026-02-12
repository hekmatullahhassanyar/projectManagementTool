package com.studentmanagement.components;

import com.studentmanagement.entities.Task;
import com.studentmanagement.filters.TaskFilter;
import java.util.List;

public interface TaskTrackingComponent {
    void addTask(Task task);
    List<Task> listTasks();
    List<Task> filterTasks(TaskFilter<Task> filter);
    List<Task> overdueTasks();
}
