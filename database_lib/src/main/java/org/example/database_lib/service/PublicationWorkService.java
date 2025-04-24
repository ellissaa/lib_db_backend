package org.example.database_lib.service;

import org.example.database_lib.model.PublicationWork;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class PublicationWorkService extends AbstractService<PublicationWork> {
    public PublicationWorkService(DaoInterface<PublicationWork> dao) {
        super(dao);
    }
}