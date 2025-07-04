package org.example.database_lib.service;

import org.example.database_lib.model.Library;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class LibraryService extends AbstractService<Library, Long> {
    public LibraryService(DaoInterface<Library, Long> dao) {
        super(dao);
    }
}