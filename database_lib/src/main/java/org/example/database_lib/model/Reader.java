package org.example.database_lib.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Reader")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(length = 50)
    private String patronymic;

    private LocalDate birthDate;

    @Column(length = 200)
    private String address;

    @Column(length = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private List<LoanJournal> loanJournals;

    public Reader() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LoanJournal> getLoanJournals() {
        return loanJournals;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setLoanJournals(List<LoanJournal> loanJournals) {
        this.loanJournals = loanJournals;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}