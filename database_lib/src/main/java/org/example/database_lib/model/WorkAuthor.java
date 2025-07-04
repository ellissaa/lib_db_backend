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
public class WorkAuthor {
    private Long workId;              // Corresponds to INTEGER NOT NULL REFERENCES Work(id)
    private Long authorId;            // Corresponds to INTEGER NOT NULL REFERENCES Author(id)

    @Override
    public String toString() {
        return "WorkAuthor{" +
                "workId=" + workId +
                ", authorId=" + authorId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkAuthor)) return false;
        WorkAuthor that = (WorkAuthor) o;
        return Objects.equals(workId, that.workId) &&
                Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workId, authorId);
    }
}
