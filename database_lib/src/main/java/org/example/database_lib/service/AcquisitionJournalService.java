package org.example.database_lib.service;

import org.example.database_lib.model.AcquisitionJournal;
import org.example.database_lib.repository.AcquisitionJournalDao;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcquisitionJournalService extends AbstractService<AcquisitionJournal, Long> {
    private final AcquisitionJournalDao dao;

    public AcquisitionJournalService(AcquisitionJournalDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<AcquisitionJournal> findByPeriod(String start, String end) {
        return dao.findByPeriod(start, end);
    }
}
