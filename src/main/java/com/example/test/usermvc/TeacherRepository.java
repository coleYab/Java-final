package com.example.test.usermvc;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends ListCrudRepository<Teacher, Long> {
    
}
