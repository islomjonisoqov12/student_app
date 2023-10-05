package com.student.student_app.mappers;

import com.student.student_app.dtos.auth.RegisterUserDTO;
import com.student.student_app.dtos.student.StudentDto;
import com.student.student_app.mappers.base.AbstractMapper;
import com.student.student_app.models.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper extends AbstractMapper<Student, StudentDto, RegisterUserDTO, StudentDto> {
    @Override
    Student fromCreateDto(RegisterUserDTO createDto);

    @Override
    @Mapping(target = "email", source = "email",  ignore = true)
    @Mapping(target = "username", source = "username", ignore = true)
    Student fromUpdateDto(StudentDto updateDto,@MappingTarget Student entity);


}
