package org.example.database_lib.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Professor")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Professor extends Reader {
    @Column(name = "university", nullable = false, length = 100)
    private String university;

    @Column(name = "department", length = 100)
    private String department;

    public Professor() {}

    public String getDepartment() {
        return department;
    }

    public String getUniversity() {
        return university;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
