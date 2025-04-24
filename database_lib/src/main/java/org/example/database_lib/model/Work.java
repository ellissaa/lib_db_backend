package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private Long id;                // Corresponds to the SERIAL PRIMARY KEY
    private String title;           // Corresponds to VARCHAR(200)
    private Integer creationYear;   // Corresponds to INTEGER
    private String genre;           // Corresponds to VARCHAR(100)

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creationYear=" + creationYear +
                ", genre='" + genre + '\'' +
                '}';
    }
}
