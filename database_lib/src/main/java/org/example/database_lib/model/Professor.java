package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    private Long readerId;            // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private String university;         // Corresponds to VARCHAR(100) NOT NULL
    private String department;         // Corresponds to VARCHAR(100)

    @Override
    public String toString() {
        return "Professor{" +
                "readerId=" + readerId +
                ", university='" + university + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
