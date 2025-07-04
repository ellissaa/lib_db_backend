package org.example.database_lib.service;

import org.example.database_lib.model.Professor;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.ProfessorDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends AbstractService<Professor, Long> {
    private final ProfessorDao dao;
    public ProfessorService(ProfessorDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Professor> findByUniversity(String university) {
        return dao.findByUniversity(university);
    }
}
