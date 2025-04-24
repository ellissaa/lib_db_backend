package org.example.database_lib.service;

import org.example.database_lib.model.Resident;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class ResidentService extends AbstractService<Resident> {
    public ResidentService(DaoInterface<Resident> dao) {
        super(dao);
    }
}