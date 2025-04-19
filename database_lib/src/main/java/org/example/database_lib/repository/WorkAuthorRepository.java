package org.example.database_lib.repository;

import org.example.database_lib.model.Author;
import org.example.database_lib.model.Work;
import org.example.database_lib.model.WorkAuthor;
import org.example.database_lib.model.WorkAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkAuthorRepository extends JpaRepository<WorkAuthor, WorkAuthorId> {

    @Query("SELECT wa.work FROM WorkAuthor wa WHERE wa.author.id = :authorId")
    List<Work> findWorksByAuthorId(@Param("authorId") Long authorId);

    @Query("SELECT wa.author FROM WorkAuthor wa WHERE wa.work.id = :workId")
    List<Author> findAuthorsByWorkId(@Param("workId") Long workId);

    boolean existsByWorkIdAndAuthorId(Long workId, Long authorId);
}
