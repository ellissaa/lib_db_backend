package org.example.database_lib.repository;

import org.example.database_lib.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByLibraryId(Long libraryId);
}