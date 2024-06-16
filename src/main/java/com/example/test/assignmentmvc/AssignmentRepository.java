package com.example.test.assignmentmvc;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends ListCrudRepository<Assignment, Long> {
    
}
