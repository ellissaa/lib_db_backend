package org.example.database_lib.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Retiree")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Retiree extends Reader {
    @Column(name = "has_benefits", nullable = false)
    private Boolean has_benefits;

    public Retiree() {
    }

    public Boolean getHas_benefits() {
        return has_benefits;
    }

    public void setHas_benefits(Boolean has_benefits) {
        this.has_benefits = has_benefits;
    }
}
