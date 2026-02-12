package com.studentmanagement.components.impl;

import com.studentmanagement.components.NotificationComponent;
import com.studentmanagement.components.TaskTrackingComponent;
import com.studentmanagement.entities.Task;
import com.studentmanagement.filters.TaskFilter;
import com.studentmanagement.services.TaskService;

import java.util.List;

public class TaskTrackingComponentImpl implements TaskTrackingComponent {

    private final TaskService taskService;
    private final NotificationComponent notification;

    public TaskTrackingComponentImpl(TaskService taskService, NotificationComponent notification) {
        this.taskService = taskService;
        this.notification = notification;
    }

    @Override
    public void addTask(Task task) {
        taskService.addTask(task);
        notification.notifyUser("Task added: " + task.getTitle());
    }

    @Override
    public List<Task> listTasks() {
        return taskService.listTasks();
    }

    @Override
    public List<Task> filterTasks(TaskFilter<Task> filter) {
        return taskService.filterTasks(filter);
    }

    @Override
    public List<Task> overdueTasks() {
        return taskService.getOverdueTasks();
    }
}
