package com.studentmanagement;

import com.studentmanagement.database.PostgresDatabase;
import com.studentmanagement.entities.Student;
import com.studentmanagement.entities.Course;
import com.studentmanagement.entities.Task;
import com.studentmanagement.entities.Submission;
import com.studentmanagement.exceptions.ConflictException;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.impl.StudentRepositoryImpl;
import com.studentmanagement.repositories.impl.CourseRepositoryImpl;
import com.studentmanagement.repositories.impl.TaskRepositoryImpl;
import com.studentmanagement.repositories.impl.SubmissionRepositoryImpl;
import com.studentmanagement.services.StudentService;
import com.studentmanagement.services.CourseService;
import com.studentmanagement.services.TaskService;
import com.studentmanagement.services.SubmissionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ✅ Step 1: Initialize database (implements IDatabase)
        PostgresDatabase database = new PostgresDatabase();

        try {
            // Just test the connection once
            database.connect().close();
            System.out.println("✅ Connected to Supabase successfully!");

            // ✅ Step 2: Wire repositories + services with the database object
            StudentService studentService = new StudentService(new StudentRepositoryImpl(database));
            CourseService courseService = new CourseService(new CourseRepositoryImpl(database));
            TaskService taskService = new TaskService(new TaskRepositoryImpl(database));
            SubmissionService submissionService = new SubmissionService(new SubmissionRepositoryImpl(database));

            // ✅ Step 3: Use Scanner for user input
            Scanner sc = new Scanner(System.in);

            try {
                System.out.print("Enter student ID: ");
                int studentId = sc.nextInt();
                sc.nextLine(); // consume newline

                System.out.print("Enter student name: ");
                String studentName = sc.nextLine();

                System.out.print("Enter student email: ");
                String studentEmail = sc.nextLine();

                Student s1 = new Student(studentId, studentName, studentEmail);
                studentService.addStudent(s1);
                System.out.println("✅ Added student: " + s1.getName());

                System.out.print("Enter course ID: ");
                int courseId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter course name: ");
                String courseName = sc.nextLine();

                Course c1 = new Course(courseId, courseName);
                courseService.addCourse(c1);
                System.out.println("✅ Added course: " + c1.getName());

                System.out.print("Enter task ID: ");
                int taskId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter task title: ");
                String taskTitle = sc.nextLine();

                System.out.print("Enter task description: ");
                String taskDesc = sc.nextLine();

                System.out.print("Enter task due year: ");
                int year = sc.nextInt();
                System.out.print("Enter task due month: ");
                int month = sc.nextInt();
                System.out.print("Enter task due day: ");
                int day = sc.nextInt();

                Task t1 = new Task(taskId, taskTitle, taskDesc, LocalDate.of(year, month, day));
                taskService.addTask(t1);
                System.out.println("✅ Added task: " + t1.getTitle());

                // Example of changing task status
                taskService.changeTaskStatus(taskId, "IN_PROGRESS");
                System.out.println("🔄 Task status changed to IN_PROGRESS");

                Submission sub1 = new Submission(5005, s1.getId(), t1.getId(), LocalDateTime.now());
                submissionService.addSubmission(sub1);
                System.out.println("✅ Added submission for student " + s1.getName());

            } catch (InvalidInputException | ConflictException | NotFoundException e) {
                System.out.println("❌ Business rule error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Unexpected error: " + e.getMessage());
            } finally {
                sc.close();
            }

        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
