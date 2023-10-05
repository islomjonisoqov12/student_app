package com.student.student_app.models;

import com.student.student_app.models.base.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "lessons")
public class Lesson extends Auditable {
    private String title;
    private String description;
    @ManyToOne(optional = false)
    private Notebook notebook;

    @OneToMany
    private List<Attachment> files;

}
