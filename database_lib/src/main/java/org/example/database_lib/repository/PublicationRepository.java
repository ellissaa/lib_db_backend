package org.example.database_lib.repository;

import org.example.database_lib.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByLibraryId(Long libraryId);
}
