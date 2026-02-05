package com.studentmanagement.tasktypes;

import com.studentmanagement.entities.Task;
import java.time.LocalDate;

public class ResearchTask extends Task {
    public ResearchTask(int id, String title, String description, LocalDate dueDate) {
        super(id, "[RESEARCH] " + title, description, dueDate);
    }
}
