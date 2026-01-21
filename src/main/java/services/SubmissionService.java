package com.studentmanagement.services;

import com.studentmanagement.entities.Submission;
import com.studentmanagement.repositories.SubmissionRepository;

import java.util.List;

public class SubmissionService {
    private final SubmissionRepository repository;

    public SubmissionService(SubmissionRepository repository) {
        this.repository = repository;
    }

    public void addSubmission(Submission submission) {
        repository.save(submission);
    }

    public Submission getSubmission(int id) {
        return repository.findById(id);
    }

    public List<Submission> listSubmissions() {
        return repository.findAll();
    }
}
