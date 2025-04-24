package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    private Long readerId;           // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private String occupation;        // Corresponds to VARCHAR(100)

    @Override
    public String toString() {
        return "Resident{" +
                "readerId=" + readerId +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
