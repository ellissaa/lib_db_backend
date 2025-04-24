package org.example.database_lib.service;

import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<T> {
    private final DaoInterface<T> dao;

    @Autowired
    protected AbstractService(DaoInterface<T> dao) {
        this.dao = dao;
    }

    public int create(T entity) {
        return dao.create(entity);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public T findById(Long id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Content with id %d not found", id
                )));
    }

    public int update(T entity) {
        return dao.update(entity);
    }

    public int delete(Long id) {
        return dao.delete(id);
    }
}

