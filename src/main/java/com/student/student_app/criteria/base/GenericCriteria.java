package com.student.student_app.criteria.base;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Objects;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GenericCriteria implements BaseGenericCriteria, Serializable {

    protected Integer page;

    protected Integer size;

    protected String sortBy;

    protected String sortDirection;

    public Sort.Direction getSortDirection() {
        return sortDirection == null || sortDirection.equals("") || sortDirection.equals("ASC")? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public String getSortBy() {
        return sortBy == null || sortBy.equals("") ? "id" :sortBy;
    }


    public Integer getPage() {
        if (this.page == null || this.page < 1) {
            page = 1;
        }
        return page;
    }

    public Integer getSize() {
        if (Objects.isNull(this.size) || this.size < 1) {
            size = 10;
        }
        return size;
    }

    @Hidden
    public PageRequest getPageable(){
        if(sortBy==null) return PageRequest.of(getPage()-1, getSize());
        return PageRequest.of(getPage()-1, getSize(), Sort.by(getSortDirection(), getSortBy()));
    }


}