package org.example.database_lib.service;

import org.example.database_lib.model.Worker;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class WorkerService extends AbstractService<Worker> {
    public WorkerService(DaoInterface<Worker> dao) {
        super(dao);
    }
}