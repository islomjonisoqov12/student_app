package com.student.student_app.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyEmailDto {
    @NotNull @NotBlank
    @NotEmpty
    private String email;

    @NotNull @NotBlank @NotEmpty
    private String code;
}
