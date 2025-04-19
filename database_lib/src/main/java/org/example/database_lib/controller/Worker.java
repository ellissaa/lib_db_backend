package org.example.database_lib.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Worker")
@PrimaryKeyJoinColumn(name = "reader_id")
public class Worker extends Reader {
    @Column(name = "profession", nullable = false, length = 100)
    private String profession;

    @Column(name = "organization", nullable = false, length = 100)
    private String organization;

    public Worker() {}

    public String getOrganization() {
        return organization;
    }

    public String getProfession() {
        return profession;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
