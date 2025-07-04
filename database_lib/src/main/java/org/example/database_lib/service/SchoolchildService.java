package org.example.database_lib.service;

import org.example.database_lib.model.Schoolchild;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.SchoolchildDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolchildService extends AbstractService<Schoolchild, Long> {
    private final SchoolchildDao dao;

    public SchoolchildService(SchoolchildDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Schoolchild> findBySchool(String school) {
        return dao.findBySchool(school);
    }
}
