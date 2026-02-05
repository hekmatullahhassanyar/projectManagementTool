package com.studentmanagement.services;

import com.studentmanagement.entities.Submission;
import com.studentmanagement.exceptions.InvalidInputException;
import com.studentmanagement.exceptions.NotFoundException;
import com.studentmanagement.repositories.SubmissionRepository;

import java.util.List;

public class SubmissionService {
    private final SubmissionRepository repository;

    public SubmissionService(SubmissionRepository repository) {
        this.repository = repository;
    }

    public void addSubmission(Submission submission) {
        if (submission.getSubmittedAt() == null) {
            throw new InvalidInputException("Submission time cannot be null");
        }
        repository.save(submission);
    }

    public Submission getSubmission(int id) {
        Submission submission = repository.findById(id);
        if (submission == null) {
            throw new NotFoundException("Submission not found");
        }
        return submission;
    }

    public List<Submission> listSubmissions() {
        return repository.findAll();
    }
}
