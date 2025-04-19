package org.example.database_lib.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Resident")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Resident extends Reader {
    @Column(name = "occupation", length = 100)
    private String occupation;

    public Resident() {
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
