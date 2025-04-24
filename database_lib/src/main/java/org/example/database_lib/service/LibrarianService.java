package org.example.database_lib.service;

import org.example.database_lib.model.Librarian;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService extends AbstractService<Librarian> {
    public LibrarianService(DaoInterface<Librarian> dao) {
        super(dao);
    }
}