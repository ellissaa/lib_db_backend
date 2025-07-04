package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationWorkId {
    private Long publicationId;       // Corresponds to INTEGER NOT NULL REFERENCES Publication(id)
    private Long workId;              // Corresponds to INTEGER NOT NULL REFERENCES Work(id)

    @Override
    public String toString() {
        return "PublicationWorkId{" +
                "publicationId=" + publicationId +
                ", workId=" + workId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicationWorkId)) return false;
        PublicationWorkId that = (PublicationWorkId) o;
        return Objects.equals(publicationId, that.publicationId) &&
                Objects.equals(workId, that.workId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, workId);
    }
}
