package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RetireeService extends ReaderService {
    public RetireeService(ReaderRepository readerRepository,
                          LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Retiree create(Retiree retiree, Long libraryId) {
        return (Retiree) createBaseReader(retiree, libraryId);
    }

    public Retiree getById(Long id) {
        return (Retiree) readerRepository.findRetireeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Retiree not found with id: " + id));
    }

    public List<Retiree> getAll() {
        return readerRepository.findAllRetirees();
    }

    public Retiree update(Long id, Retiree retireeDetails) {
        Retiree retiree = getById(id);
        retiree.setName(retireeDetails.getName());
        retiree.setSurname(retireeDetails.getSurname());
        retiree.setPatronymic(retireeDetails.getPatronymic());
        retiree.setBirthDate(retireeDetails.getBirthDate());
        retiree.setAddress(retireeDetails.getAddress());
        retiree.setPhone(retireeDetails.getPhone());
        retiree.setHasBenefits(retireeDetails.getHasBenefits());
        return (Retiree) readerRepository.save(retiree);
    }

    public List<Retiree> getByBenefitsStatus(boolean has_benefits) {
        return readerRepository.findRetireesByHasBenefits(has_benefits);
    }
}
