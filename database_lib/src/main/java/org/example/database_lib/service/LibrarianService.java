package org.example.database_lib.service;

import org.example.database_lib.model.Librarian;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.LibrarianDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService extends AbstractService<Librarian, Long> {
    private final LibrarianDao dao;

    public LibrarianService(LibrarianDao dao) {
        super(dao);
        this.dao = dao;
    }

    public Long countServed(Long id, String start, String end) {
        return dao.countServed(id, start, end);
    }

    public List<Librarian> findByHall(Long hall) {
        return dao.findByHall(hall);
    }
}
