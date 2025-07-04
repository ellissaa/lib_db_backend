package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Librarian {
    private Long id;                // Corresponds to the SERIAL PRIMARY KEY
    private String name;            // Corresponds to VARCHAR(50)
    private String surname;         // Corresponds to VARCHAR(50)
    private String patronymic;      // Corresponds to VARCHAR(50)
    private String position;        // Corresponds to VARCHAR(100)
    private Long hallId;            // Corresponds to INTEGER NOT NULL REFERENCES Hall(id)

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", hallId=" + hallId +
                '}';
    }
}
