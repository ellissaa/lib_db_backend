package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private Long readerId;            // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private String profession;         // Corresponds to VARCHAR(100) NOT NULL
    private String organization;       // Corresponds to VARCHAR(100) NOT NULL

    @Override
    public String toString() {
        return "Worker{" +
                "readerId=" + readerId +
                ", profession='" + profession + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
