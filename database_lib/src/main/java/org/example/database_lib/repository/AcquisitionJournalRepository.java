package org.example.database_lib.repository;

import org.example.database_lib.model.AcquisitionJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquisitionJournalRepository extends JpaRepository<AcquisitionJournal, Long> {
}