package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long readerId;            // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private String university;         // Corresponds to VARCHAR(100) NOT NULL
    private String faculty;            // Corresponds to VARCHAR(100) NOT NULL
    private String studentGroup;       // Corresponds to VARCHAR(20)

    @Override
    public String toString() {
        return "Student{" +
                "readerId=" + readerId +
                ", university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", studentGroup='" + studentGroup + '\'' +
                '}';
    }
}
