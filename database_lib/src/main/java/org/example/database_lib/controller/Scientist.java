package org.example.database_lib.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Scientist")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Scientist extends Reader {
    @Column(name = "academic_degree", nullable = false, length = 100)
    private String academicDegree;

    @Column(name = "workplace", length = 100)
    private String workplace;

    public Scientist() {}

    public String getAcademicDegree() {
        return academicDegree;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
}
