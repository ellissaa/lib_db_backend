package org.example.database_lib.service;

import org.example.database_lib.model.Professor;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends AbstractService<Professor> {
    public ProfessorService(DaoInterface<Professor> dao) {
        super(dao);
    }
}