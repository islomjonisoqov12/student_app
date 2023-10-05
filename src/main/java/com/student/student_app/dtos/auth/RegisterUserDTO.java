package com.student.student_app.dtos.auth;

import com.student.student_app.config.Constants;
import com.student.student_app.enums.StudyType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

/**
 * A DTO representing a user, with his authorities.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO implements Serializable {

    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    private String email;
    private LocalDate birthDate;

    private short status;

    @NotNull
    private String phoneNumber;
    private StudyType studyType;
    private Map<String, String> educationInfo;

    public static final int PASSWORD_MIN_LENGTH = 6;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Override
    public String toString() {
        return "RegisterUserDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
