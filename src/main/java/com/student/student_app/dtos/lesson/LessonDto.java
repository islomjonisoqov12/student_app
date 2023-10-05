package com.student.student_app.dtos.lesson;

import com.student.student_app.models.Attachment;
import com.student.student_app.models.Notebook;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    @NotNull @NotBlank @NotEmpty
    private String id;
    @NotNull @NotBlank @NotEmpty
    private String title;
    private String description;
    @Hidden
    private List<String> fileIds;
}
