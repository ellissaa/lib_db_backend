package org.example.database_lib.repository;

import org.example.database_lib.model.Schoolchild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolchildRepository extends JpaRepository<Schoolchild, Long> {
}
