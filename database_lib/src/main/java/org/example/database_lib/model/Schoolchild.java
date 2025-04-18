package org.example.database_lib.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Schoolchild")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Schoolchild extends Reader {
    @Column(name = "school", nullable = false, length = 100)
    private String school;

    @Column(name = "grade", nullable = false, length = 20)
    private String grade;

    public Schoolchild() {}

    public String getGrade() {
        return grade;
    }

    public String getSchool() {
        return school;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
