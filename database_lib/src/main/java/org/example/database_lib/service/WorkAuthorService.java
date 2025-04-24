package org.example.database_lib.service;

import org.example.database_lib.model.WorkAuthor;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class WorkAuthorService extends AbstractService<WorkAuthor> {
    public WorkAuthorService(DaoInterface<WorkAuthor> dao) {
        super(dao);
    }
}