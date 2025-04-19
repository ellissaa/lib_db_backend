package org.example.database_lib.controller;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class WorkAuthorId implements Serializable {
    private Long workId;
    private Long authorId;

    public WorkAuthorId() {}

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getWorkId() {
        return workId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
