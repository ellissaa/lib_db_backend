package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final PublicationTypeRepository publicationTypeRepository;
    private final LibraryRepository libraryRepository;

    public PublicationService(PublicationRepository publicationRepository,
                              PublicationTypeRepository publicationTypeRepository,
                              LibraryRepository libraryRepository) {
        this.publicationRepository = publicationRepository;
        this.publicationTypeRepository = publicationTypeRepository;
        this.libraryRepository = libraryRepository;
    }

    public Publication create(Publication publication, Long publicationTypeId, Long libraryId) {
        PublicationType type = publicationTypeRepository.findById(publicationTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication type not found"));
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));

        publication.setPublicationType(type);
        publication.setLibrary(library);
        return publicationRepository.save(publication);
    }

    public Publication getById(Long id) {
        return publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication not found"));
    }

    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    public List<Publication> getByLibrary(Long libraryId) {
        return publicationRepository.findByLibraryId(libraryId);
    }

    public Publication update(Long id, Publication publicationDetails) {
        Publication publication = getById(id);
        publication.setTitle(publicationDetails.getTitle());
        publication.setPublicationYear(publicationDetails.getPublicationYear());
        publication.setStorageHallNumber(publicationDetails.getStorageHallNumber());
        publication.setShelf(publicationDetails.getShelf());
        publication.setRack(publicationDetails.getRack());
        publication.setLoanFlag(publicationDetails.getLoanFlag());
        publication.setLoanPeriodDays(publicationDetails.getLoanPeriodDays());
        return publicationRepository.save(publication);
    }

    public void delete(Long id) {
        Publication publication = getById(id);
        publicationRepository.delete(publication);
    }
}
