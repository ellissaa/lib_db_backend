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
public class LoanJournal {
    private Long id;                       // Corresponds to the SERIAL PRIMARY KEY
    private LocalDate loanDate;            // Corresponds to DATE NOT NULL
    private LocalDate expectedReturnDate;  // Corresponds to DATE NOT NULL
    private LocalDate actualReturnDate;    // Corresponds to DATE
    private Long librarianId;               // Corresponds to INTEGER NOT NULL REFERENCES Librarian(id)
    private Long copyId;                    // Corresponds to INTEGER NOT NULL REFERENCES Copy(id)
    private Long readerId;                  // Corresponds to INTEGER NOT NULL REFERENCES Reader(id)

    @Override
    public String toString() {
        return "LoanJournal{" +
                "id=" + id +
                ", loanDate=" + loanDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                ", librarianId=" + librarianId +
                ", copyId=" + copyId +
                ", readerId=" + readerId +
                '}';
    }
}
