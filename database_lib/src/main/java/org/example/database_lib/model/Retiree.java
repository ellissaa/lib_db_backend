package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Retiree {
    private Long readerId;            // Corresponds to INTEGER PRIMARY KEY REFERENCES Reader(id)
    private Boolean hasBenefits;       // Corresponds to BOOLEAN NOT NULL

    @Override
    public String toString() {
        return "Retiree{" +
                "readerId=" + readerId +
                ", hasBenefits=" + hasBenefits +
                '}';
    }
}
