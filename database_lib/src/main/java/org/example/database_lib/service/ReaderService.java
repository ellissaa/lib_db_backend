package org.example.database_lib.service;

import org.example.database_lib.model.Reader;
import org.example.database_lib.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader updateReader(Long id, Reader readerDetails) {
        Reader reader = readerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Reader not found"));
        reader.setName(readerDetails.getName());
        reader.setCategory(readerDetails.getCategory());
        reader.setBooksBorrowed(readerDetails.getBooksBorrowed());
        reader.setLibrary(readerDetails.getLibrary());
        return readerRepository.save(reader);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
