package org.example.database_lib.service;

import org.example.database_lib.model.Schoolchild;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class SchoolchildService extends AbstractService<Schoolchild> {
    public SchoolchildService(DaoInterface<Schoolchild> dao) {
        super(dao);
    }
}