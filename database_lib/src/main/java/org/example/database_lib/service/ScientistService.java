package org.example.database_lib.service;

import org.example.database_lib.model.Scientist;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class ScientistService extends AbstractService<Scientist> {
    public ScientistService(DaoInterface<Scientist> dao) {
        super(dao);
    }
}