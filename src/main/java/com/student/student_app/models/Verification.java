package com.student.student_app.models;

import com.student.student_app.models.base.BaseGenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "verifications")
public class Verification implements BaseGenericEntity {
    @Id
    private String email;
    private String code;
    @Column(updatable = false, columnDefinition = " timestamp")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Verification(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
