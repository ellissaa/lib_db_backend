package org.example.database_lib.service;

import org.example.database_lib.model.Reader;
import org.example.database_lib.model.ReaderPublication;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.ReaderDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService extends AbstractService<Reader, Long> {
    private final ReaderDao dao;
    public ReaderService(ReaderDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Reader> findByWork(String work) {
        return dao.findByWork(work);
    }

    public List<Reader> findByPublication(String publication) {
        return dao.findByPublication(publication);
    }

    public List<ReaderPublication> findGotWork(String start, String end, String work) {
        return dao.findGotWork(start, end, work);
    }

    public List<Reader> findByLibrarian(Long librarian, String start, String end) {
        return dao.findByLibrarian(librarian, start, end);
    }

    public List<Reader> findOverdue() {
        return dao.findOverdue();
    }

    public List<Reader> findNotVisited(String start, String end) {
        return dao.findNotVisited(start, end);
    }
}
