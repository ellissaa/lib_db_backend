package org.example.database_lib.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PublicationType")
public class PublicationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "publicationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications;

    public PublicationType() {}

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}