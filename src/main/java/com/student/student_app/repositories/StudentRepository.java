package com.student.student_app.repositories;

import com.student.student_app.dtos.student.StudentDto;
import com.student.student_app.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUsername(String username);

    Boolean existsByUsername(String username);
}
