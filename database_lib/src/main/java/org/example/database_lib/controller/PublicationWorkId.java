package org.example.database_lib.controller;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PublicationWorkId implements Serializable {
    private Long publicationId;
    private Long workId;

    public PublicationWorkId() {}

    public Long getPublicationId() {
        return publicationId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }
}
