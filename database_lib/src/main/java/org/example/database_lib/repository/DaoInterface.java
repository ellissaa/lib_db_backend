package org.example.database_lib.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DaoInterface<T, ID> {
    T create(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    int update(T entity);
    int delete(ID id);
}
