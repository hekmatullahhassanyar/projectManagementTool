package com.studentmanagement.components.impl;

import com.studentmanagement.components.NotificationComponent;
import com.studentmanagement.components.ProjectManagementComponent;
import com.studentmanagement.entities.Project;
import com.studentmanagement.entities.Student;
import com.studentmanagement.entities.Task;
import com.studentmanagement.tasktypes.TaskFactory;
import com.studentmanagement.tasktypes.TaskType;

import java.time.LocalDate;

public class ProjectManagementComponentImpl implements ProjectManagementComponent {

    private final NotificationComponent notification;

    public ProjectManagementComponentImpl(NotificationComponent notification) {
        this.notification = notification;
    }

    @Override
    public Project buildDemoProject(Student member, Task task) {
        Project p = new Project.Builder()
                .setId(1)
                .setName("OOP Milestone 2 Demo")
                .addMember(member)
                .addTask(task)
                .addTag("java")
                .addTag("teamwork")
                .build();

        notification.notifyUser("Project built: " + p.getName());
        return p;
    }

    @Override
    public Task buildBugTask() {
        Task bugTask = TaskFactory.createTask(
                TaskType.BUG,
                10001,
                "Login crash",
                "Fix NullPointerException on login",
                LocalDate.now().plusDays(3)
        );
        notification.notifyUser("Factory created task: " + bugTask.getTitle());
        return bugTask;
    }
}
