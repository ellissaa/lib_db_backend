package org.example.database_lib.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Work_Author")
public class WorkAuthor {
    @EmbeddedId
    private WorkAuthorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("workId")
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    public void setId(WorkAuthorId id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public Work getWork() {
        return work;
    }

    public WorkAuthorId getId() {
        return id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
