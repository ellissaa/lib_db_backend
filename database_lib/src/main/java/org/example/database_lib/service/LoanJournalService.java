package org.example.database_lib.service;

import org.example.database_lib.model.LoanJournal;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class LoanJournalService extends AbstractService<LoanJournal, Long> {
    public LoanJournalService(DaoInterface<LoanJournal, Long> dao) {
        super(dao);
    }
}