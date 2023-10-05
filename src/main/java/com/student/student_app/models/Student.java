package com.student.student_app.models;


import com.student.student_app.enums.StudyType;
import com.student.student_app.models.base.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
public class Student extends Auditable {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
//    @Column(nullable = false)
    private LocalDate birthDate;
    @Enumerated(value = EnumType.STRING)
    private StudyType studyType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> educationInfo;



}
