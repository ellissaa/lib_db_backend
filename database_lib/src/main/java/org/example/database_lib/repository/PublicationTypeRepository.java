package org.example.database_lib.repository;

import org.example.database_lib.model.PublicationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationTypeRepository extends JpaRepository<PublicationType, Long> {
}
