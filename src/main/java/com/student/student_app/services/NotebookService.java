package com.student.student_app.services;

import com.student.student_app.criteria.NotebookCriteria;
import com.student.student_app.dtos.notebook.NotebookCDto;
import com.student.student_app.dtos.notebook.NotebookDto;

import java.util.List;

public interface NotebookService {
    NotebookDto saveNotebook(NotebookCDto dto);

    List<NotebookDto> getNotebooks(NotebookCriteria criteria);

    NotebookDto updateNotebook(NotebookDto dto);

    void deleteNotebook(String id);
}
