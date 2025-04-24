package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schoolchild {
    private Long readerId;            // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private String school;             // Corresponds to VARCHAR(100) NOT NULL
    private String grade;              // Corresponds to VARCHAR(20) NOT NULL

    @Override
    public String toString() {
        return "Schoolchild{" +
                "readerId=" + readerId +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
