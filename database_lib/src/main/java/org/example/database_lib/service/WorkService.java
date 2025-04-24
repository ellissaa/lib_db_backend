package org.example.database_lib.service;

import org.example.database_lib.model.Work;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class WorkService extends AbstractService<Work> {
    public WorkService(DaoInterface<Work> dao) {
        super(dao);
    }
}