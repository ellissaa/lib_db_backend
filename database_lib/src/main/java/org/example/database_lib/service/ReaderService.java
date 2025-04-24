package org.example.database_lib.service;

import org.example.database_lib.model.Reader;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class ReaderService extends AbstractService<Reader> {
    public ReaderService(DaoInterface<Reader> dao) {
        super(dao);
    }
}