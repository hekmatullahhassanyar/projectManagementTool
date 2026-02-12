package com.studentmanagement.controllers;

import com.studentmanagement.components.ProjectManagementComponent;
import com.studentmanagement.components.TaskTrackingComponent;
import com.studentmanagement.entities.Project;
import com.studentmanagement.entities.Student;
import com.studentmanagement.entities.Task;
import com.studentmanagement.filters.DeadlineFilter;

import java.time.LocalDate;
import java.util.List;

public class AppController {

    private final ProjectManagementComponent projects;
    private final TaskTrackingComponent tasks;

    public AppController(ProjectManagementComponent projects, TaskTrackingComponent tasks) {
        this.projects = projects;
        this.tasks = tasks;
    }

    public void runDemo(Student student, Task mainTask) {
        System.out.println("✅ UI/Controller started. Depends only on component interfaces.");

        // 1) Builder demo via component
        Project p = projects.buildDemoProject(student, mainTask);
        System.out.println("✅ Built project (Builder): " + p);

        // 2) List tasks
        System.out.println("\n📋 All tasks:");
        tasks.listTasks().forEach(t -> System.out.println(" - " + t.getTitle()));

        // 3) Generic filter (TaskFilter<Task>) + lambda option
        System.out.println("\n🧹 Filtered tasks (deadline before 2026-02-20):");
        DeadlineFilter deadlineFilter = new DeadlineFilter(LocalDate.of(2026, 2, 20));
        List<Task> filtered = tasks.filterTasks(deadlineFilter);
        filtered.forEach(t -> System.out.println(" - " + t.getTitle() + " | due: " + t.getDueDate()));

        // 4) Factory demo via component
        Task bugTask = projects.buildBugTask();
        tasks.addTask(bugTask);
        System.out.println("✅ Added factory task: " + bugTask.getTitle());
    }
}
