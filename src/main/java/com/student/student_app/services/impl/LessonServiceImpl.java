package com.student.student_app.services.impl;

import com.student.student_app.criteria.LessonCriteria;
import com.student.student_app.dtos.lesson.LessonCD;
import com.student.student_app.dtos.lesson.LessonDto;
import com.student.student_app.exceptions.NotFoundException;
import com.student.student_app.exceptions.ValidationException;
import com.student.student_app.mappers.LessonMapper;
import com.student.student_app.models.Attachment;
import com.student.student_app.models.Lesson;
import com.student.student_app.models.Notebook;
import com.student.student_app.repositories.AttachmentRepository;
import com.student.student_app.repositories.LessonRepository;
import com.student.student_app.repositories.NoteBookRepository;
import com.student.student_app.services.AttachmentService;
import com.student.student_app.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository repository;
    private final AttachmentService attachmentService;
    private final AttachmentRepository attachmentRepository;
    private final NoteBookRepository noteBookRepository;
    private final LessonMapper mapper;
    @Override
    public LessonDto saveLesson(LessonCD dto) {
        Notebook byId = noteBookRepository.findById(dto.getNotebookId()).orElseThrow(() -> new NotFoundException("notebook not found by id "+dto.getNotebookId()));
        List<Attachment> attachments = attachmentRepository.findAllById(dto.getAttachmentIds());
        Lesson lesson = mapper.fromCreateDto(dto, attachments, byId);
        repository.save(lesson);
        return mapper.toDto(lesson);
    }

    @Override
    public List<LessonDto> getLessons(LessonCriteria criteria) {
        List<Lesson> all = repository.findAll(criteria.getPageable(), criteria.getSearch(), criteria.getNotebookId());
        return all.stream().map(mapper::toDto).toList();
    }

    @Override
    public LessonDto updateLesson(LessonDto dto) {
        if (dto.getId() == null) {
            throw new ValidationException("id must be given");
        }
        Lesson lesson = repository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("lesson not found"));
        mapper.fromUpdateDto(dto, lesson);
        repository.save(lesson);
        return mapper.toDto(lesson);
    }

    @Override
    public void deleteLesson(String id) {
        Optional<Lesson> byId = repository.findById(id);
        if (byId.isPresent()) {
            Lesson lesson = byId.get();
            String[] ids = (String[]) lesson.getFiles().stream().map(Attachment::getId).toArray();
            attachmentService.deleteAttachments(ids);
        }
    }

    @Override
    public LessonDto getViaId(String id) {
        Lesson lesson = repository.findById(id).orElseThrow(() -> new NotFoundException("lesson not found"));
        return mapper.toDto(lesson);
    }
}
