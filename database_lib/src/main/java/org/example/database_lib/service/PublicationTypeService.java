package org.example.database_lib.service;

import org.example.database_lib.model.PublicationType;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class PublicationTypeService extends AbstractService<PublicationType> {
    public PublicationTypeService(DaoInterface<PublicationType> dao) {
        super(dao);
    }
}