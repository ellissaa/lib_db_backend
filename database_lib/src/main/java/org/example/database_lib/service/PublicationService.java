package org.example.database_lib.service;

import org.example.database_lib.model.Publication;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.PublicationDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService extends AbstractService<Publication, Long> {
    private final PublicationDao dao;

    public PublicationService(PublicationDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Publication> findByReaderReg(String start, String end, Long readerId) {
        return dao.findByReaderReg(start, end, readerId);
    }

    public List<Publication> findByReaderNotReg(String start, String end, Long readerId) {
        return dao.findByReaderNotReg(start, end, readerId);
    }

    public List<Publication> findByShelf(Long lib, Long hall, Long rack, Long shelf) {
        return dao.findByShelf(lib, hall, rack, shelf);
    }

    public List<Publication> findByWork(String work) {
        return dao.findByWork(work);
    }

    public List<Publication> findByAuthor(Long author) {
        return dao.findByAuthor(author);
    }
}
