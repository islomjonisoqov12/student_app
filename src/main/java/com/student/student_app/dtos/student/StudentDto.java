package com.student.student_app.dtos.student;

import com.student.student_app.enums.StudyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String email;
    private LocalDate birthDate;
    private StudyType studyType;
    private Map<String, String> educationInfo;
}
