package com.student.student_app.repositories;

import com.student.student_app.models.Notebook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteBookRepository extends JpaRepository<Notebook, String> {
    @Query(value = "select * from notebooks where name like :search", nativeQuery = true)
    List<Notebook> findAll(PageRequest pageable, String search);

    @Query(value = "select count(*)>0 from lessons where notebook_id = :id", nativeQuery = true)
    boolean hasLesson(String id);
}
