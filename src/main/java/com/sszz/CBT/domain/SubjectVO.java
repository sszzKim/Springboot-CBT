package com.sszz.CBT.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_SUBJECT_TB")
public class SubjectVO {

    @Id
    String subjectId;
    String name;

    public SubjectVO() {
    }

    public SubjectVO(String subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
