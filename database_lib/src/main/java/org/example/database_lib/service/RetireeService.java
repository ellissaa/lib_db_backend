package org.example.database_lib.service;

import org.example.database_lib.model.Retiree;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class RetireeService extends AbstractService<Retiree> {
    public RetireeService(DaoInterface<Retiree> dao) {
        super(dao);
    }
}