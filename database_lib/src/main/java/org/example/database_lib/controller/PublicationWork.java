package org.example.database_lib.controller;

import jakarta.persistence.*;

@Entity
@Table(name = "Publication_Work")
public class PublicationWork {
    @EmbeddedId
    private PublicationWorkId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("publicationId")
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("workId")
    @JoinColumn(name = "work_id")
    private Work work;

    public PublicationWork() {}

    public void setWork(Work work) {
        this.work = work;
    }

    public Work getWork() {
        return work;
    }

    public void setId(PublicationWorkId id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public PublicationWorkId getId() {
        return id;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
