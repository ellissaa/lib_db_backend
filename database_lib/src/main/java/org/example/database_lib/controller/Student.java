package org.example.database_lib.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Student extends Reader {
    @Column(name = "university", nullable = false, length = 100)
    private String university;

    @Column(name = "faculty", nullable = false, length = 100)
    private String faculty;

    @Column(name = "student_group", nullable = false, length = 100)
    private String student_group;

    public Student() {}

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudent_group() {
        return student_group;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setStudent_group(String student_group) {
        this.student_group = student_group;
    }
}
