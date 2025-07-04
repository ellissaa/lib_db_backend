package org.example.database_lib.service;

import org.example.database_lib.model.PublicationType;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class PublicationTypeService extends AbstractService<PublicationType, Long> {
    public PublicationTypeService(DaoInterface<PublicationType, Long> dao) {
        super(dao);
    }
}