package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderPublication {
    private Long id;                // Corresponds to the SERIAL PRIMARY KEY
    private String name;            // Corresponds to VARCHAR(50)
    private String surname;         // Corresponds to VARCHAR(50)
    private String patronymic;      // Corresponds to VARCHAR(50)
    private LocalDate birthDate;    // Corresponds to DATE
    private String address;         // Corresponds to VARCHAR(200)
    private String phone;           // Corresponds to VARCHAR(20)
    private Long libraryId;         // Corresponds to INTEGER NOT NULL REFERENCES Library(id)
    private String publication;

    @Override
    public String toString() {
        return "ReaderPublication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", libraryId=" + libraryId + '\'' +
                ", publication=" + publication +
                '}';
    }
}
