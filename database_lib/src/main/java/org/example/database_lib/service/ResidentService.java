package org.example.database_lib.service;

import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.Resident;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.ResidentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService extends AbstractService<Resident, Long> {
    private final ResidentDao dao;

    public ResidentService(ResidentDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Resident> findByOccupation(String occupation) {
        return dao.findByOccupation(occupation);
    }
}
