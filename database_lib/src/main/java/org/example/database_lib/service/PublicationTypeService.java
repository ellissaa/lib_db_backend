package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PublicationTypeService {
    private final PublicationTypeRepository publicationTypeRepository;

    public PublicationTypeService(PublicationTypeRepository publicationTypeRepository) {
        this.publicationTypeRepository = publicationTypeRepository;
    }

    public PublicationType create(PublicationType publicationType) {
        return publicationTypeRepository.save(publicationType);
    }

    public PublicationType getById(Long id) {
        return publicationTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication type not found"));
    }

    public List<PublicationType> getAll() {
        return publicationTypeRepository.findAll();
    }

    public PublicationType update(Long id, PublicationType typeDetails) {
        PublicationType type = getById(id);
        type.setName(typeDetails.getName());
        return publicationTypeRepository.save(type);
    }

    public void delete(Long id) {
        PublicationType type = getById(id);
        publicationTypeRepository.delete(type);
    }
}