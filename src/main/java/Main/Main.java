package com.studentmanagement.Main;
import com.studentmanagement.database.PostgresDatabase;
import com.studentmanagement.entities.Student;
import com.studentmanagement.entities.Course;
import com.studentmanagement.entities.Task;
import com.studentmanagement.entities.Submission;
import com.studentmanagement.repositories.impl.StudentRepositoryImpl;
import com.studentmanagement.repositories.impl.CourseRepositoryImpl;
import com.studentmanagement.repositories.impl.TaskRepositoryImpl;
import com.studentmanagement.repositories.impl.SubmissionRepositoryImpl;
import com.studentmanagement.services.StudentService;
import com.studentmanagement.services.CourseService;
import com.studentmanagement.services.TaskService;
import com.studentmanagement.services.SubmissionService;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    static void main(String[] args) {
        // ✅ Step 1: Connect to Supabase
        PostgresDatabase database = new PostgresDatabase();
        try (Connection conn = database.connect()) {
            System.out.println("✅ Connected to Supabase successfully!");
        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
            return; // stop if connection fails
        }

        // ✅ Step 2: Wire repositories + services
        StudentService studentService = new StudentService(new StudentRepositoryImpl(database));
        CourseService courseService = new CourseService(new CourseRepositoryImpl(database));
        TaskService taskService = new TaskService(new TaskRepositoryImpl(database));
        SubmissionService submissionService = new SubmissionService(new SubmissionRepositoryImpl(database));

        // ✅ Step 3: Demo inserts

        Student s2 = new Student(5, "ehsan", "ehsan420@gmail.com");
        studentService.addStudent(s2);
        System.out.println("✅ Added student: " + s2.getName());

        Course c1 = new Course(105, "cyber security");

        courseService.addCourse(c1);
        System.out.println("✅ Added course: " + c1.getName());

        Task t1 = new Task(1005, "Project Report", "Write final project report", LocalDate.of(2026, 2, 15));
        taskService.addTask(t1);
        System.out.println("✅ Added task: " + t1.getTitle());

        Submission sub1 = new Submission(5005, s2.getId(), t1.getId(), LocalDateTime.now());
        submissionService.addSubmission(sub1);
        System.out.println("✅ Added submission for student " + s2.getName());

        // ✅ Step 4: Retrieve by ID
        Student foundStudent = studentService.getStudent(5);
        System.out.println("🔍 Found student: " + foundStudent.getName() + " (" + foundStudent.getEmail() + ")");

        Course foundCourse = courseService.getCourse(105);
        System.out.println("🔍 Found course: " + foundCourse.getName());

        Task foundTask = taskService.getTask(1005);
        System.out.println("🔍 Found task: " + foundTask.getTitle() + " due " + foundTask.getDueDate());

        Submission foundSubmission = submissionService.getSubmission(5005);
        System.out.println("🔍 Found submission: studentId=" + foundSubmission.getStudentId() +
                ", taskId=" + foundSubmission.getTaskId() +
                ", submittedAt=" + foundSubmission.getSubmittedAt());

        // ✅ Step 5: List all
        System.out.println("\n📋 All students:");
        studentService.listStudents().forEach(st -> System.out.println(" - " + st.getName()));

        System.out.println("\n📋 All courses:");
        courseService.listCourses().forEach(co -> System.out.println(" - " + co.getName()));

        System.out.println("\n📋 All tasks:");
        taskService.listTasks().forEach(ts -> System.out.println(" - " + ts.getTitle()));

        System.out.println("\n📋 All submissions:");
        submissionService.listSubmissions().forEach(sb -> System.out.println(" - Submission ID: " + sb.getId()));
    }
}
