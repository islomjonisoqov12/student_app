package com.student.student_app.models.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable implements BaseGenericEntity {
    @Id
    @Column(unique = true)
    private String id = UUID.randomUUID().toString();

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "varchar")
    private String createdBy;

    @ColumnDefault(value = "true")
    private boolean deleted;

    private short status;

}
