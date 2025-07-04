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
public class AcquisitionJournal {
    private Long id;                    // Corresponds to the SERIAL PRIMARY KEY
    private LocalDate acquisitionDate;  // Corresponds to DATE NOT NULL
    private Integer quantity;            // Corresponds to INTEGER NOT NULL
    private String operationType;       // Corresponds to VARCHAR(3)
    private Long publicationId;          // Corresponds to INTEGER NOT NULL REFERENCES Publication(id)

    @Override
    public String toString() {
        return "AcquisitionJournal{" +
                "id=" + id +
                ", acquisitionDate=" + acquisitionDate +
                ", quantity=" + quantity +
                ", operationType='" + operationType + '\'' +
                ", publicationId=" + publicationId +
                '}';
    }
}
