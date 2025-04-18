package org.example.database_lib.repository;

import org.example.database_lib.model.WorkAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkAuthorRepository extends JpaRepository<WorkAuthor, Long> {
}
