package com.studentmanagement.tasktypes;

import com.studentmanagement.entities.Task;
import java.time.LocalDate;

public class BugTask extends Task {
    public BugTask(int id, String title, String description, LocalDate dueDate) {
        super(id, "[BUG] " + title, description, dueDate);
    }
}
