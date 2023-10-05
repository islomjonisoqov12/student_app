package com.student.student_app.services.impl;

import com.student.student_app.dtos.auth.RegisterUserDTO;
import com.student.student_app.dtos.auth.VerifyEmailDto;
import com.student.student_app.dtos.student.StudentDto;
import com.student.student_app.exceptions.NotFoundException;
import com.student.student_app.exceptions.ValidationException;
import com.student.student_app.exceptions.user.UserNotFoundException;
import com.student.student_app.mappers.StudentMapper;
import com.student.student_app.models.Student;
import com.student.student_app.models.Verification;
import com.student.student_app.repositories.StudentRepository;
import com.student.student_app.repositories.VerificationRepository;
import com.student.student_app.security.SecurityUtils;
import com.student.student_app.services.StudentService;
import com.student.student_app.services.EmailSender;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final VerificationRepository verificationRepository;
    private final StudentRepository studentRepository;
    private final EmailSender emailSender;
    private final StudentMapper mapper;
    private final PasswordEncoder encoder;




    /**
     * verify email with code
     * @param dto
     * @return true if code is match
     */
    @Override
    public Boolean verifyEmail(VerifyEmailDto dto) {
        Verification verification = verificationRepository.findById(dto.getEmail()).orElseThrow(() -> new NotFoundException("email not found from verifications"));
        return dto.getCode().equals(verification.getCode());
    }

    /**
     * send code to email and then save code to db
     * @param email - recipient
     */
    @Override
    public void sendCode(String email) {
        String code = String.valueOf(RandomUtils.nextInt(100_000, 999_999));
        emailSender.sendEmailVerification(email, code);
        verificationRepository.save(new Verification(email, code));
    }

    @Override
    public StudentDto saveUser(RegisterUserDTO dto) {
        Student student = mapper.fromCreateDto(dto);
        student.setPassword(encoder.encode(student.getPassword()));
        student.setStatus((short)1);
        studentRepository.save(student);
        return mapper.toDto(student);
    }

    @Override
    public StudentDto updateUser(StudentDto dto) {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("user must be authenticated"));
        Student student = studentRepository.findByUsername(login).orElseThrow(() -> new UserNotFoundException("user not found by id"));
        mapper.fromUpdateDto(dto, student);
        studentRepository.save(student);
        return mapper.toDto(student);
    }

    @Override
    public StudentDto getCurrentUser() {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("user must be authenticated"));
        Student student = studentRepository.findByUsername(login).orElseThrow(() -> new UserNotFoundException("user not found by id"));
        return mapper.toDto(student);
    }
}
