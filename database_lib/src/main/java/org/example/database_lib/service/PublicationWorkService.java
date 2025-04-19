package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PublicationWorkService {
    private final PublicationWorkRepository publicationWorkRepository;
    private final PublicationRepository publicationRepository;
    private final WorkRepository workRepository;

    public PublicationWorkService(PublicationWorkRepository publicationWorkRepository,
                                  PublicationRepository publicationRepository,
                                  WorkRepository workRepository) {
        this.publicationWorkRepository = publicationWorkRepository;
        this.publicationRepository = publicationRepository;
        this.workRepository = workRepository;
    }

    public PublicationWork create(Long publicationId, Long workId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication not found with id: " + publicationId));
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found with id: " + workId));

        PublicationWork publicationWork = new PublicationWork();
        publicationWork.setPublication(publication);
        publicationWork.setWork(work);

        return publicationWorkRepository.save(publicationWork);
    }

    public PublicationWork getById(PublicationWorkId id) {
        return publicationWorkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Publication-Work relationship not found for publicationId: " +
                                id.getPublicationId() + " and workId: " + id.getWorkId()));
    }

    public List<PublicationWork> getAll() {
        return publicationWorkRepository.findAll();
    }

    public List<Publication> getPublicationsByWork(Long workId) {
        return publicationWorkRepository.findPublicationsByWorkId(workId);
    }

    public List<Work> getWorksByPublication(Long publicationId) {
        return publicationWorkRepository.findWorksByPublicationId(publicationId);
    }

    public void delete(PublicationWorkId id) {
        PublicationWork publicationWork = getById(id);
        publicationWorkRepository.delete(publicationWork);
    }

    public void deleteByPublicationAndWork(Long publicationId, Long workId) {
        PublicationWorkId id = new PublicationWorkId(publicationId, workId);
        delete(id);
    }
}