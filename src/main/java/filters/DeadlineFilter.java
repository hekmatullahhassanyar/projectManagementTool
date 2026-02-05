package com.studentmanagement.filters;

import com.studentmanagement.entities.Task;
import java.time.LocalDate;

public class DeadlineFilter implements TaskFilter<Task> {

    private LocalDate date;

    public DeadlineFilter(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean filter(Task task) {
        return task.getDueDate().isBefore(date);
    }
}
