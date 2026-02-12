package com.studentmanagement.mainapp;

import com.studentmanagement.components.NotificationComponent;
import com.studentmanagement.components.ProjectManagementComponent;
import com.studentmanagement.components.TaskTrackingComponent;
import com.studentmanagement.components.impl.NotificationComponentImpl;
import com.studentmanagement.components.impl.ProjectManagementComponentImpl;
import com.studentmanagement.components.impl.TaskTrackingComponentImpl;
import com.studentmanagement.controllers.AppController;

import com.studentmanagement.database.PostgresDatabase;

import com.studentmanagement.entities.Student;
import com.studentmanagement.entities.Task;

import com.studentmanagement.repositories.impl.StudentRepositoryImpl;
import com.studentmanagement.repositories.impl.CourseRepositoryImpl;
import com.studentmanagement.repositories.impl.TaskRepositoryImpl;
import com.studentmanagement.repositories.impl.SubmissionRepositoryImpl;

import com.studentmanagement.services.StudentService;
import com.studentmanagement.services.CourseService;
import com.studentmanagement.services.TaskService;
import com.studentmanagement.services.SubmissionService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // 1) DB + repositories + services (same as before)
        PostgresDatabase database = new PostgresDatabase();

        StudentService studentService = new StudentService(new StudentRepositoryImpl(database));
        CourseService courseService = new CourseService(new CourseRepositoryImpl(database));
        TaskService taskService = new TaskService(new TaskRepositoryImpl(database));
        SubmissionService submissionService = new SubmissionService(new SubmissionRepositoryImpl(database));

        // 2) Components
        NotificationComponent notification = new NotificationComponentImpl();
        TaskTrackingComponent taskComponent = new TaskTrackingComponentImpl(taskService, notification);
        ProjectManagementComponent projectComponent = new ProjectManagementComponentImpl(notification);

        // 3) Controller (UI depends only on interfaces)
        AppController controller = new AppController(projectComponent, taskComponent);

        // 4) Demo data (avoid duplicates: choose new ids each run)
        int sid = (int)(System.currentTimeMillis() % 100000);
        Student s2 = new Student(sid, "ehsan", "ehsan" + sid + "@gmail.com");
        studentService.addStudent(s2);

        int tid = sid + 200000;
        Task t1 = new Task(tid, "Project Report", "Write final project report", LocalDate.of(2026, 2, 15));
        taskService.addTask(t1);

        // 5) Run demo through controller
        controller.runDemo(s2, t1);
    }
}
