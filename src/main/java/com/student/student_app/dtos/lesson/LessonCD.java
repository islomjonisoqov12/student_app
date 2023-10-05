package com.student.student_app.dtos.lesson;

import com.student.student_app.models.Notebook;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonCD {
    private String title;
    private String description;
    private String notebookId;
    @OneToMany
    private List<String> attachmentIds;
}
