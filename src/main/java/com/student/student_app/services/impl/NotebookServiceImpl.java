package com.student.student_app.services.impl;

import com.student.student_app.criteria.NotebookCriteria;
import com.student.student_app.dtos.notebook.NotebookCDto;
import com.student.student_app.dtos.notebook.NotebookDto;
import com.student.student_app.exceptions.NotFoundException;
import com.student.student_app.exceptions.ValidationException;
import com.student.student_app.exceptions.user.UserNotFoundException;
import com.student.student_app.mappers.NotebookMapper;
import com.student.student_app.models.Attachment;
import com.student.student_app.models.Notebook;
import com.student.student_app.models.Student;
import com.student.student_app.repositories.AttachmentRepository;
import com.student.student_app.repositories.NoteBookRepository;
import com.student.student_app.repositories.StudentRepository;
import com.student.student_app.security.SecurityUtils;
import com.student.student_app.services.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookServiceImpl implements NotebookService {
    private final NoteBookRepository repository;
    private final StudentRepository studentRepository;
    private final AttachmentRepository attachmentRepository;
    private final NotebookMapper mapper;
    @Override
    public NotebookDto saveNotebook(NotebookCDto dto) {
        String username = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UserNotFoundException("please authenticate before save notebook"));
        Student student = studentRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user not found by username"));
        Attachment attachment = attachmentRepository.findById(dto.getAttachmentId()).orElseThrow(() -> new NotFoundException("attachment not found"));
        Notebook notebook = mapper.fromCreateDto(dto, attachment, student);
        repository.save(notebook);
        return mapper.toDto(notebook);
    }

    @Override
    public List<NotebookDto> getNotebooks(NotebookCriteria criteria) {
        return repository.findAll(criteria.getPageable(), criteria.getSearch()).stream().map(mapper::toDto).toList();
    }

    @Override
    public NotebookDto updateNotebook(NotebookDto dto) {
        if (dto.getId() == null) throw new ValidationException("notebook id must be given");
        Notebook notebook = repository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("notebook not found by id" + dto.getId()));
        boolean ownStudent = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("authenticate before update")).equalsIgnoreCase(notebook.getStudent().getUsername());
        //protect update dto
        if(!ownStudent){
            throw new ValidationException("this lesson is belong to another student");
        }
        mapper.fromUpdateDto(dto, notebook);
        repository.save(notebook);
        return null;
    }

    @Override
    public void deleteNotebook(String id) {
        if(repository.hasLesson(id)) {
            throw new ValidationException("there some lessons in this notebook. please delete them and try again");
        }
        repository.deleteById(id);

    }
}
