package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@Transactional
public class LibrarianService {
    private final LibrarianRepository librarianRepository;
    private final HallRepository hallRepository;

    public LibrarianService(LibrarianRepository librarianRepository,
                            HallRepository hallRepository) {
        this.librarianRepository = librarianRepository;
        this.hallRepository = hallRepository;
    }

    public Librarian create(Librarian librarian, Long hallId) {
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found"));
        librarian.setHall(hall);
        return librarianRepository.save(librarian);
    }

    public Librarian getById(Long id) {
        return librarianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Librarian not found"));
    }

    public List<Librarian> getAll() {
        return librarianRepository.findAll();
    }

    public List<Librarian> getByHall(Long hallId) {
        return librarianRepository.findByHallId(hallId);
    }

    public Librarian update(Long id, Librarian librarianDetails) {
        Librarian librarian = getById(id);
        librarian.setName(librarianDetails.getName());
        librarian.setSurname(librarianDetails.getSurname());
        librarian.setPatronymic(librarianDetails.getPatronymic());
        librarian.setPosition(librarianDetails.getPosition());
        return librarianRepository.save(librarian);
    }

    public void delete(Long id) {
        Librarian librarian = getById(id);
        librarianRepository.delete(librarian);
    }
}