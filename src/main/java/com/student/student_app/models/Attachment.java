package com.student.student_app.models;

import com.student.student_app.models.base.Auditable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "attachments")
public class Attachment extends Auditable {
    private String originalName;
    private String name;
    private String path;
    private String contentType;
    private Long size;

    @Override
    public String getId() {
        return super.getId();
    }
}
