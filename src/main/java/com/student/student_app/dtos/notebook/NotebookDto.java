package com.student.student_app.dtos.notebook;

import com.student.student_app.dtos.student.StudentDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotebookDto {
    @NotNull @NotBlank @NotEmpty
    private String id;
    @NotNull @NotBlank @NotEmpty
    private String name;
    private String attachmentId;
    private StudentDto studentDto;
}
