package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.*;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AcquisitionJournalService {
    private final AcquisitionJournalRepository acquisitionJournalRepository;
    private final PublicationRepository publicationRepository;

    public AcquisitionJournalService(AcquisitionJournalRepository acquisitionJournalRepository,
                                     PublicationRepository publicationRepository) {
        this.acquisitionJournalRepository = acquisitionJournalRepository;
        this.publicationRepository = publicationRepository;
    }

    public AcquisitionJournal create(AcquisitionJournal acquisitionJournal, Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication not found"));
        acquisitionJournal.setPublication(publication);
        return acquisitionJournalRepository.save(acquisitionJournal);
    }

    public AcquisitionJournal getById(Long id) {
        return acquisitionJournalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acquisition record not found"));
    }

    public List<AcquisitionJournal> getAll() {
        return acquisitionJournalRepository.findAll();
    }

    public List<AcquisitionJournal> getByPublication(Long publicationId) {
        return acquisitionJournalRepository.findByPublicationId(publicationId);
    }

    public AcquisitionJournal update(Long id, AcquisitionJournal journalDetails) {
        AcquisitionJournal journal = getById(id);
        journal.setAcquisitionDate(journalDetails.getAcquisitionDate());
        journal.setQuantity(journalDetails.getQuantity());
        journal.setOperationType(journalDetails.getOperationType());
        return acquisitionJournalRepository.save(journal);
    }

    public void delete(Long id) {
        AcquisitionJournal journal = getById(id);
        acquisitionJournalRepository.delete(journal);
    }
}