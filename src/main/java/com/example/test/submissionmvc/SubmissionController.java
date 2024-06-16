package com.example.test.submissionmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/submission")
public class SubmissionController {
    @Autowired
    private final SubmissionRepository submissionRepository;

    public SubmissionController(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @GetMapping("")
    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }

    @PostMapping("")
    public void create(@RequestBody Submission submission) {
        submissionRepository.save(submission);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Submission submission, @RequestParam Long id) {
        submissionRepository.save(submission);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        submissionRepository.delete(submissionRepository.findById(id).get());
    }
}
