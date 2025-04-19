package org.example.database_lib.controller;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "AcquisitionJournal")
public class AcquisitionJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acquisition_date", nullable = false)
    private LocalDate acquisitionDate;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "operation_type", length = 3)
    private String operationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    public AcquisitionJournal() {}

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}