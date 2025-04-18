package org.example.database_lib.repository;

import org.example.database_lib.model.LoanJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanJournalRepository extends JpaRepository<LoanJournal, Long> {
}