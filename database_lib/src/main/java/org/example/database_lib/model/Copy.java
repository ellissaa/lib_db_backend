package org.example.database_lib.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Copy")
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @OneToMany(mappedBy = "copy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanJournal> loanJournals;

    public Copy() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLoanJournals(List<LoanJournal> loanJournals) {
        this.loanJournals = loanJournals;
    }

    public List<LoanJournal> getLoanJournals() {
        return loanJournals;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
