package org.example.database_lib.service;

import org.example.database_lib.model.Work;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.WorkDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService extends AbstractService<Work, Long> {
    private final WorkDao dao;

    public WorkService(WorkDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Work> findMostPopular() {
        return dao.findMostPopular();
    }
}
