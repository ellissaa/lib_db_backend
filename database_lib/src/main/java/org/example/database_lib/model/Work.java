package org.example.database_lib.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "creation_year")
    private Integer creationYear;

    @Column(name = "genre", nullable = false, length = 100)
    private String genre;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PublicationWork> publicationWorks;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkAuthor> workAuthors;

    public Work() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public List<PublicationWork> getPublicationWorks() {
        return publicationWorks;
    }

    public List<WorkAuthor> getWorkAuthors() {
        return workAuthors;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublicationWorks(List<PublicationWork> publicationWorks) {
        this.publicationWorks = publicationWorks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWorkAuthors(List<WorkAuthor> workAuthors) {
        this.workAuthors = workAuthors;
    }
}
