package com.studentmanagement.repositories;

import com.studentmanagement.entities.Submission;
import java.util.List;

public interface SubmissionRepository {
    void save(Submission submission);
    Submission findById(int id);
    List<Submission> findAll();
}
