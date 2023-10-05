package com.student.student_app.mappers;

import com.student.student_app.dtos.notebook.NotebookCDto;
import com.student.student_app.dtos.notebook.NotebookDto;
import com.student.student_app.dtos.student.StudentDto;
import com.student.student_app.mappers.base.AbstractMapper;
import com.student.student_app.models.Attachment;
import com.student.student_app.models.Notebook;
import com.student.student_app.models.Student;
import org.mapstruct.*;
import org.mapstruct.control.MappingControl;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotebookMapper extends AbstractMapper<Notebook, NotebookDto, NotebookCDto, NotebookDto> {

    @Mapping(target = "student", expression = "java(student)")
    @Mapping(target = "attachment", expression = "java(file)")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "name", source = "createDto.name")
    Notebook fromCreateDto(NotebookCDto createDto, Attachment file, Student student);

    @Mapping(target = "studentDto",ignore = true, qualifiedByName = "toStudentDto")
    @Mapping(target = "attachmentId", source = "attachment.id")
    @Override
    NotebookDto toDto(Notebook entity);

    @Named("toStudentDto")
    StudentDto fromStudentEntity(Student entity);
}
