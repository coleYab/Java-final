package com.example.test.submissionmvc;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends ListCrudRepository<Submission, Long> { 
}
