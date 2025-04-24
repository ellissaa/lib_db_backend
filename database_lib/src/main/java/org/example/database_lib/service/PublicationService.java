package org.example.database_lib.service;

import org.example.database_lib.model.Publication;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class PublicationService extends AbstractService<Publication> {
    public PublicationService(DaoInterface<Publication> dao) {
        super(dao);
    }
}