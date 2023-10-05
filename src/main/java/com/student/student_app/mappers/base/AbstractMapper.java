package com.student.student_app.mappers.base;



import com.student.student_app.models.base.BaseGenericEntity;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface AbstractMapper<
        E extends BaseGenericEntity,
        D,
        CD,
        UP
        > extends BaseGenericMapper {
    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UP updateDto, @MappingTarget E entity);
}
