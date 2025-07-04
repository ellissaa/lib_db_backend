package org.example.database_lib.service;

import org.example.database_lib.model.Scientist;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.ScientistDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScientistService extends AbstractService<Scientist, Long> {
    private final ScientistDao dao;

    public ScientistService(ScientistDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Scientist> findByWorkplace(String workplace) {
        return dao.findByWorkplace(workplace);
    }
}
