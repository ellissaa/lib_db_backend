package org.example.database_lib.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Publication")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "storage_hall_number", nullable = false)
    private Integer storageHallNumber;

    @Column(name = "shelf", nullable = false)
    private Integer shelf;

    @Column(name = "rack", nullable = false)
    private Integer rack;

    @Column(name = "loan_flag", columnDefinition = "boolean default false")
    private Boolean loanFlag = false;

    @Column(name = "loan_period_days")
    private Integer loanPeriodDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_type_id", nullable = false)
    private PublicationType publicationType;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcquisitionJournal> acquisitionJournals;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Copy> copies;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PublicationWork> publicationWorks;

    public Publication() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPublicationWorks(List<PublicationWork> publicationWorks) {
        this.publicationWorks = publicationWorks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<PublicationWork> getPublicationWorks() {
        return publicationWorks;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    public Boolean getLoanFlag() {
        return loanFlag;
    }

    public Integer getLoanPeriodDays() {
        return loanPeriodDays;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Integer getRack() {
        return rack;
    }

    public Integer getShelf() {
        return shelf;
    }

    public Integer getStorageHallNumber() {
        return storageHallNumber;
    }

    public List<AcquisitionJournal> getAcquisitionJournals() {
        return acquisitionJournals;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setAcquisitionJournals(List<AcquisitionJournal> acquisitionJournals) {
        this.acquisitionJournals = acquisitionJournals;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    public void setLoanFlag(Boolean loanFlag) {
        this.loanFlag = loanFlag;
    }

    public void setLoanPeriodDays(Integer loanPeriodDays) {
        this.loanPeriodDays = loanPeriodDays;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setRack(Integer rack) {
        this.rack = rack;
    }

    public void setShelf(Integer shelf) {
        this.shelf = shelf;
    }

    public void setStorageHallNumber(Integer storageHallNumber) {
        this.storageHallNumber = storageHallNumber;
    }
}
