package com.student.student_app.services;

import com.student.student_app.controllers.AuthController;
import com.student.student_app.dtos.auth.RegisterUserDTO;
import com.student.student_app.dtos.auth.VerifyEmailDto;
import com.student.student_app.dtos.student.StudentDto;

/**
 * Service Interface for managing {@link AuthController}.
 */
public interface StudentService {


    Boolean verifyEmail(VerifyEmailDto dto);

    void sendCode(String email);

    StudentDto saveUser(RegisterUserDTO dto);

    StudentDto updateUser(StudentDto dto);

    StudentDto getCurrentUser();
}
