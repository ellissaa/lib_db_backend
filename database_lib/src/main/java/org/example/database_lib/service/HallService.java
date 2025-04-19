package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@Transactional
public class HallService {
    private final HallRepository hallRepository;
    private final LibraryRepository libraryRepository;

    public HallService(HallRepository hallRepository, LibraryRepository libraryRepository) {
        this.hallRepository = hallRepository;
        this.libraryRepository = libraryRepository;
    }

    public Hall create(Hall hall, Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        hall.setLibrary(library);
        return hallRepository.save(hall);
    }

    public Hall getById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
    }

    public List<Hall> getAll() {
        return hallRepository.findAll();
    }

    public List<Hall> getByLibraryId(Long libraryId) {
        return hallRepository.findByLibraryId(libraryId);
    }

    public Hall update(Long id, Hall hallDetails) {
        Hall hall = getById(id);
        hall.setName(hallDetails.getName());
        hall.setDescription(hallDetails.getDescription());
        return hallRepository.save(hall);
    }

    public void delete(Long id) {
        Hall hall = getById(id);
        hallRepository.delete(hall);
    }
}