package org.example.database_lib.service;

import org.example.database_lib.model.Library;
import org.example.database_lib.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {



    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Optional<Library> getLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public Library updateLibrary(Long id, Library libraryDetails) {
        Library library = libraryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Library not found"));
        library.setName(libraryDetails.getName());
        library.setAddress(libraryDetails.getAddress());
        return libraryRepository.save(library);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}
