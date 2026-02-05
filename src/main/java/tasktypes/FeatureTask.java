package com.studentmanagement.tasktypes;

import com.studentmanagement.entities.Task;
import java.time.LocalDate;

public class FeatureTask extends Task {
    public FeatureTask(int id, String title, String description, LocalDate dueDate) {
        super(id, "[FEATURE] " + title, description, dueDate);
    }
}
