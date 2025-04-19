package org.example.database_lib.repository;

import org.example.database_lib.model.Publication;
import org.example.database_lib.model.PublicationWork;
import org.example.database_lib.model.PublicationWorkId;
import org.example.database_lib.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicationWorkRepository extends JpaRepository<PublicationWork, PublicationWorkId> {

    @Query("SELECT pw.publication FROM PublicationWork pw WHERE pw.work.id = :workId")
    List<Publication> findPublicationsByWorkId(@Param("workId") Long workId);

    @Query("SELECT pw.work FROM PublicationWork pw WHERE pw.publication.id = :publicationId")
    List<Work> findWorksByPublicationId(@Param("publicationId") Long publicationId);

    boolean existsByPublicationIdAndWorkId(Long publicationId, Long workId);
}
