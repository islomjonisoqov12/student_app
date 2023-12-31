package com.student.student_app.controllers;

import com.student.student_app.dtos.student.StudentDto;
import com.student.student_app.models.Student;
import com.student.student_app.repositories.StudentRepository;
import com.student.student_app.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Tag(name = "Student Controller", description = "apis for students")
public class StudentController {
    private final Logger log = LoggerFactory.getLogger(StudentController.class);


    private final StudentRepository studentRepository;
    private final StudentService studentService;

    /**
     * Update student
     * @param dto - student updated info
     * @return saved student info
     */
    @PatchMapping("/update")
    @PreAuthorize(value = "isFullyAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "update student", description = "update api for students")
    public ResponseEntity<StudentDto> saveUser(@Valid @RequestBody StudentDto dto){
        log.debug("student info: {}", dto.toString());
        StudentDto studentDto = studentService.updateUser(dto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Check username
     * @param username - student updated info
     * @return check username already exists
     */
    @GetMapping("/username_exists")
    @Operation(summary = "username exists", description = "check username already exists")
    public ResponseEntity<Boolean> saveUser(@RequestParam String username){
        log.debug("check username: {}", username);
        return ResponseEntity.ok(studentRepository.existsByUsername(username));
    }

}
