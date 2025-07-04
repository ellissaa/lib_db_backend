package org.example.database_lib.service;

import org.example.database_lib.model.Copy;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class CopyService extends AbstractService<Copy, Long> {
    public CopyService(DaoInterface<Copy, Long> dao) {
        super(dao);
    }
}