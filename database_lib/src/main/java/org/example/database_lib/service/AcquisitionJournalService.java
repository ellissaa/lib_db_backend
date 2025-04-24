package org.example.database_lib.service;

import org.example.database_lib.model.AcquisitionJournal;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class AcquisitionJournalService extends AbstractService<AcquisitionJournal> {
    public AcquisitionJournalService(DaoInterface<AcquisitionJournal> dao) {
        super(dao);
    }
}