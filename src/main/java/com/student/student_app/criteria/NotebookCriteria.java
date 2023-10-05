package com.student.student_app.criteria;

import com.student.student_app.criteria.base.GenericCriteria;

public class NotebookCriteria extends GenericCriteria {
    // search by name,
    String search;

    public String getSearch() {
        return search==null? "%" : "%"+search.toLowerCase()+"%";
    }
    public void setSearch(String search) {
        this.search = search;
    }

}
