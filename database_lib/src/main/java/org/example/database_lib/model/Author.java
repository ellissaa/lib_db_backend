package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;                // Corresponds to the SERIAL PRIMARY KEY
    private String name;            // Corresponds to VARCHAR(50)
    private String surname;         // Corresponds to VARCHAR(50)
    private String patronymic;      // Corresponds to VARCHAR(50)
    private String country;         // Corresponds to VARCHAR(100)
    private Integer birthYear;      // Corresponds to INTEGER

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", country='" + country + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
