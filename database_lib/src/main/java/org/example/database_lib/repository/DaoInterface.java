package org.example.database_lib.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DaoInterface<T> {
    int create(T entity);
    List<T> findAll();
    Optional<T> findById(Long id);
    int update(T entity);
    int delete(Long id);
}
