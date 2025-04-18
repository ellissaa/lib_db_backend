package org.example.database_lib.repository;

import org.example.database_lib.model.Retiree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetireeRepository extends JpaRepository<Retiree, Long> {
}
