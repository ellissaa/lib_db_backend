package org.example.database_lib.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Librarian")
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "patronymic", length = 50)
    private String patronymic;

    @Column(name = "position", length = 100)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanJournal> loanJournals;

    public Librarian() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setLoanJournals(List<LoanJournal> loanJournals) {
        this.loanJournals = loanJournals;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public List<LoanJournal> getLoanJournals() {
        return loanJournals;
    }

    public Hall getHall() {
        return hall;
    }

    public String getPosition() {
        return position;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}