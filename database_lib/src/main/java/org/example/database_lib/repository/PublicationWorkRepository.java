package org.example.database_lib.repository;

import org.example.database_lib.model.PublicationWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationWorkRepository extends JpaRepository<PublicationWork, Long> {
}
