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
import com.studentmanagement.filters.DeadlineFilter;
import com.studentmanagement.entities.Project;
import com.studentmanagement.tasktypes.TaskFactory;
import com.studentmanagement.tasktypes.TaskType;


import java.util.List;


import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    static void main() {
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

        Student s2 = new Student(8, "ehsan", "ehsan676@gmail.com");
        studentService.addStudent(s2);
        System.out.println("✅ Added student: " + s2.getName());

        Course c1 = new Course(108, "cyber security");

        courseService.addCourse(c1);
        System.out.println("✅ Added course: " + c1.getName());

        Task t1 = new Task(1008, "Project Report", "Write final project report", LocalDate.of(2026, 2, 15));
        taskService.addTask(t1);
        System.out.println("✅ Added task: " + t1.getTitle());

        Submission sub1 = new Submission(5008, s2.getId(), t1.getId(), LocalDateTime.now());
        submissionService.addSubmission(sub1);
        System.out.println("✅ Added submission for student " + s2.getName());

        // ✅ Step 4: Retrieve by ID
        Student foundStudent = studentService.getStudent(8);
        System.out.println("🔍 Found student: " + foundStudent.getName() + " (" + foundStudent.getEmail() + ")");

        Course foundCourse = courseService.getCourse(108);
        System.out.println("🔍 Found course: " + foundCourse.getName());

        Task foundTask = taskService.getTask(1008);
        System.out.println("🔍 Found task: " + foundTask.getTitle() + " due " + foundTask.getDueDate());

        Submission foundSubmission = submissionService.getSubmission(5008);
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

        System.out.println("\n🧹 Filtered tasks (deadline before 2026-02-20):");

        DeadlineFilter deadlineFilter = new DeadlineFilter(LocalDate.of(2026, 2, 20));
        List<Task> filtered = taskService.filterTasks(deadlineFilter);

        for (Task t : filtered) {
            System.out.println(" - " + t.getTitle() + " | due: " + t.getDueDate());
        }

        System.out.println("\n⏰ Overdue tasks:");
        List<Task> overdue = taskService.getOverdueTasks();
        overdue.forEach(t -> System.out.println(" - " + t.getTitle() + " | " + t.getDueDate()));

        Project p1 = new Project.Builder()
                .setId(1)
                .setName("OOP Milestone 2 Demo")
                .addMember(s2)
                .addTask(t1)
                .addTag("java")
                .addTag("teamwork")
                .build();

        System.out.println("✅ Built project (Builder): " + p1);


        Task bugTask = TaskFactory.createTask(
                TaskType.BUG,
                9999,
                "Login crash",
                "Fix NullPointerException on login",
                LocalDate.now().plusDays(3)
        );

        taskService.addTask(bugTask);
        System.out.println("✅ Added factory task: " + bugTask.getTitle());

    }

}
