package org.example.database_lib.service;

import org.example.database_lib.model.Retiree;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.RetireeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetireeService extends AbstractService<Retiree, Long> {
    private final RetireeDao dao;

    public RetireeService(RetireeDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Retiree> findByBenefits(Boolean benefits) {
        return dao.findByBenefits(benefits);
    }
}
