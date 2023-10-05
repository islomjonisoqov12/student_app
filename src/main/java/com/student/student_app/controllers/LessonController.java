package com.student.student_app.controllers;

import com.student.student_app.criteria.LessonCriteria;
import com.student.student_app.criteria.NotebookCriteria;
import com.student.student_app.dtos.lesson.LessonCD;
import com.student.student_app.dtos.lesson.LessonDto;
import com.student.student_app.dtos.notebook.NotebookCDto;
import com.student.student_app.dtos.notebook.NotebookDto;
import com.student.student_app.services.LessonService;
import com.student.student_app.services.NotebookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
@Tag(name = "Lesson Controller", description = "apis for lessons")
public class LessonController {
    private final LessonService service;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize(value = "isAuthenticated()")
    public HttpEntity<LessonDto> saveLesson(@Valid @RequestBody LessonCD dto){
        LessonDto lesson = service.saveLesson(dto);
        return ResponseEntity.ok(lesson);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public HttpEntity<LessonDto> getNotebooks(@RequestParam String id){
        LessonDto lesson = service.getViaId(id);
        return ResponseEntity.ok(lesson);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public HttpEntity<List<LessonDto>> getNotebooks(LessonCriteria criteria){
        List<LessonDto> lessons = service.getLessons(criteria);
        return ResponseEntity.ok(lessons);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping
    public HttpEntity<LessonDto> updateLesson(@Valid @RequestBody LessonDto dto){
        LessonDto lessonDto = service.updateLesson(dto);
        return ResponseEntity.ok(lessonDto);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public void delete(@RequestParam String id){
        service.deleteLesson(id);
    }
}
