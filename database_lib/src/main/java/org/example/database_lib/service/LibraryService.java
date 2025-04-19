package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.Library;
import org.example.database_lib.repository.LibraryRepository;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@Transactional
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library create(Library library) {
        return libraryRepository.save(library);
    }

    public Library getById(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found with id: " + id));
    }

    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    public Library update(Long id, Library libraryDetails) {
        Library library = getById(id);
        library.setName(libraryDetails.getName());
        library.setAddress(libraryDetails.getAddress());
        library.setPhone(libraryDetails.getPhone());
        library.setEmail(libraryDetails.getEmail());
        return libraryRepository.save(library);
    }

    public void delete(Long id) {
        Library library = getById(id);
        libraryRepository.delete(library);
    }
}