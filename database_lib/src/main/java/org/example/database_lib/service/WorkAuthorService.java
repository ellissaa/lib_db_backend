package org.example.database_lib.service;

import org.example.database_lib.model.WorkAuthor;
import org.example.database_lib.model.WorkAuthorId;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class WorkAuthorService extends AbstractService<WorkAuthor, WorkAuthorId> {
    public WorkAuthorService(DaoInterface<WorkAuthor, WorkAuthorId> dao) {
        super(dao);
    }
}
