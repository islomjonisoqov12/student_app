package com.student.student_app.criteria;

import com.student.student_app.criteria.base.GenericCriteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonCriteria extends GenericCriteria {

    private String search;
    private String notebookId;

    public String getSearch() {
        return search==null? "%" : "%"+search.toLowerCase()+"%";
    }
}
