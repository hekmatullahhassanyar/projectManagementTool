package com.studentmanagement.tasktypes;

import com.studentmanagement.entities.Task;
import java.time.LocalDate;

public class TaskFactory {

    public static Task createTask(TaskType type,
                                  int id,
                                  String title,
                                  String description,
                                  LocalDate dueDate) {
        return switch (type) {
            case BUG -> new BugTask(id, title, description, dueDate);
            case FEATURE -> new FeatureTask(id, title, description, dueDate);
            case RESEARCH -> new ResearchTask(id, title, description, dueDate);
        };
    }
}
