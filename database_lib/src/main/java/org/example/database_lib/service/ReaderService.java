package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReaderService {
    protected final ReaderRepository readerRepository;
    protected final LibraryRepository libraryRepository;

    public ReaderService(ReaderRepository readerRepository,
                         LibraryRepository libraryRepository) {
        this.readerRepository = readerRepository;
        this.libraryRepository = libraryRepository;
    }

    protected Reader createBaseReader(Reader reader, Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        reader.setLibrary(library);
        return readerRepository.save(reader);
    }

    public Reader getById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reader not found with id: " + id));
    }

    public void delete(Long id) {
        Reader reader = getById(id);
        readerRepository.delete(reader);
    }
}
