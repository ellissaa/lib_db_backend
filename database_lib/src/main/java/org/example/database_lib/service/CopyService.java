package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@Transactional
public class CopyService {
    private final CopyRepository copyRepository;
    private final PublicationRepository publicationRepository;

    public CopyService(CopyRepository copyRepository,
                       PublicationRepository publicationRepository) {
        this.copyRepository = copyRepository;
        this.publicationRepository = publicationRepository;
    }

    public Copy create(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication not found"));
        Copy copy = new Copy();
        copy.setPublication(publication);
        return copyRepository.save(copy);
    }

    public Copy getById(Long id) {
        return copyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Copy not found"));
    }

    public List<Copy> getAll() {
        return copyRepository.findAll();
    }

    public List<Copy> getByPublication(Long publicationId) {
        return copyRepository.findByPublicationId(publicationId);
    }

    public void delete(Long id) {
        Copy copy = getById(id);
        copyRepository.delete(copy);
    }
}
