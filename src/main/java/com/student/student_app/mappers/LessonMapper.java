package com.student.student_app.mappers;

import com.student.student_app.dtos.lesson.LessonCD;
import com.student.student_app.dtos.lesson.LessonDto;
import com.student.student_app.mappers.base.AbstractMapper;
import com.student.student_app.models.Attachment;
import com.student.student_app.models.Lesson;
import com.student.student_app.models.Notebook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LessonMapper extends AbstractMapper<Lesson, LessonDto, LessonCD, LessonDto> {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "files", expression = "java(files)")
    @Mapping(target = "notebook", expression = "java(notebook)")
    Lesson fromCreateDto(LessonCD createDto, List<Attachment> files, Notebook notebook);

    @Override
    @Mapping(target = "fileIds", expression = "java(entity.getFiles().stream().map(a -> a.getId()).toList())")
    LessonDto toDto(Lesson entity);

    @Override
    Lesson fromUpdateDto(LessonDto updateDto, Lesson entity);
}
