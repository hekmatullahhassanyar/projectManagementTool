package com.studentmanagement.components;

import com.studentmanagement.entities.Project;
import com.studentmanagement.entities.Student;
import com.studentmanagement.entities.Task;

public interface ProjectManagementComponent {
    Project buildDemoProject(Student member, Task task);
    Task buildBugTask(); // Factory demo
}
