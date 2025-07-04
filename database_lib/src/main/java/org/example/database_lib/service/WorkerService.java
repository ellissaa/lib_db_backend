package org.example.database_lib.service;

import org.example.database_lib.model.Worker;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.WorkerDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService extends AbstractService<Worker, Long> {
    private final WorkerDao dao;

    public WorkerService(WorkerDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Worker> findByOrganization(String organization) {
        return dao.findByOrganization(organization);
    }
}
