package org.example.database_lib.repository;

import org.example.database_lib.model.Scientist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScientistRepository extends JpaRepository<Scientist, Long> {
}