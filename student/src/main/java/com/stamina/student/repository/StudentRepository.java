package com.stamina.student.repository;

import com.stamina.student.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findBySchoolId(String schoolId);
}
