package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Copy {
    private Long id;                // Corresponds to the SERIAL PRIMARY KEY
    private Long publicationId;     // Corresponds to INTEGER NOT NULL REFERENCES Publication(id)

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", publicationId=" + publicationId +
                '}';
    }
}

