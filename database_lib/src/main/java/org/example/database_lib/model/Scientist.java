package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Scientist {
    private Long readerId;            // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private String academicDegree;     // Corresponds to VARCHAR(100) NOT NULL
    private String workplace;          // Corresponds to VARCHAR(100)

    @Override
    public String toString() {
        return "Scientist{" +
                "readerId=" + readerId +
                ", academicDegree='" + academicDegree + '\'' +
                ", workplace='" + workplace + '\'' +
                '}';
    }
}
