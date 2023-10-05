package com.student.student_app.repositories;

import com.student.student_app.models.Lesson;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String> {
    @Query(value = "select * from lessons where title ilike :search and (notebook_id = :notebookId or :notebookId is null)", nativeQuery = true)
    List<Lesson> findAll(PageRequest pageable, String search, String notebookId);
}
