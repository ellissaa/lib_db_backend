package org.example.database_lib.service;

import org.example.database_lib.model.Hall;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class HallService extends AbstractService<Hall, Long> {
    public HallService(DaoInterface<Hall, Long> dao) {
        super(dao);
    }
}