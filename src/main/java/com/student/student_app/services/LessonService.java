package com.student.student_app.services;

import com.student.student_app.criteria.LessonCriteria;
import com.student.student_app.dtos.lesson.LessonCD;
import com.student.student_app.dtos.lesson.LessonDto;

import java.util.List;

public interface LessonService {

    LessonDto saveLesson(LessonCD dto);

    List<LessonDto> getLessons(LessonCriteria criteria);

    LessonDto updateLesson(LessonDto dto);

    void deleteLesson(String id);

    LessonDto getViaId(String id);
}
