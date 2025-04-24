package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hall {
    private Long id;                // Corresponds to the SERIAL PRIMARY KEY
    private String name;            // Corresponds to VARCHAR(100)
    private String description;     // Corresponds to TEXT
    private Long libraryId;         // Corresponds to INTEGER NOT NULL REFERENCES Library(id)

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", libraryId=" + libraryId +
                '}';
    }
}
