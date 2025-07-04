package org.example.database_lib.service;

import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<T, ID> {
    private final DaoInterface<T, ID> dao;

    @Autowired
    protected AbstractService(DaoInterface<T, ID> dao) {
        this.dao = dao;
    }

    public T create(T entity) {
        return dao.create(entity);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public T findById(ID id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Content with id %s not found", id
                )));
    }

    public int update(T entity) {
        return dao.update(entity);
    }

    public int delete(ID id) {
        return dao.delete(id);
    }
}

