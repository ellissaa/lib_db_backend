package org.example.database_lib.service;

import org.example.database_lib.model.PublicationWork;
import org.example.database_lib.model.PublicationWorkId;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class PublicationWorkService extends AbstractService<PublicationWork, PublicationWorkId> {
    public PublicationWorkService(DaoInterface<PublicationWork, PublicationWorkId> dao) {
        super(dao);
    }
}
