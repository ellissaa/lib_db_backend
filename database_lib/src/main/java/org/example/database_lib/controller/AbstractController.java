package org.example.database_lib.controller;

import org.example.database_lib.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class AbstractController<T> {
    private final AbstractService<T> service;

    @Autowired
    protected AbstractController(AbstractService<T> service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody T entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public T findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping()
    public int update(@RequestBody T entity) {
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

