package org.example.database_lib.service;

import org.example.database_lib.model.Author;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractService<Author, Long> {
    public AuthorService(DaoInterface<Author, Long> dao) {
        super(dao);
    }
}