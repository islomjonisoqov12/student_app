package com.student.student_app.models;

import com.student.student_app.models.base.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "notebooks")
public class Notebook extends Auditable {
    @ManyToOne(optional = false)
    private Student student;
    private String name;
    @OneToOne
    private Attachment attachment;
}
