package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    private Long id;                        // Corresponds to the SERIAL PRIMARY KEY
    private String title;                   // Corresponds to VARCHAR(200)
    private Integer publicationYear;        // Corresponds to INTEGER
    private Integer storageHallNumber;      // Corresponds to INTEGER NOT NULL
    private Integer shelf;                  // Corresponds to INTEGER NOT NULL
    private Integer rack;                   // Corresponds to INTEGER NOT NULL
    private Boolean loanFlag;                // Corresponds to BOOLEAN DEFAULT FALSE
    private Integer loanPeriodDays;         // Corresponds to INTEGER
    private Long libraryId;                 // Corresponds to INTEGER NOT NULL REFERENCES Library(id)
    private Long publicationTypeId;         // Corresponds to INTEGER NOT NULL REFERENCES PublicationType(id)

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", storageHallNumber=" + storageHallNumber +
                ", shelf=" + shelf +
                ", rack=" + rack +
                ", loanFlag=" + loanFlag +
                ", loanPeriodDays=" + loanPeriodDays +
                ", libraryId=" + libraryId +
                ", publicationTypeId=" + publicationTypeId +
                '}';
    }
}
