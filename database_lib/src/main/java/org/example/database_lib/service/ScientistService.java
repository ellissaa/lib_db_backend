package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ScientistService extends ReaderService {
    public ScientistService(ReaderRepository readerRepository,
                            LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Scientist create(Scientist scientist, Long libraryId) {
        return (Scientist) createBaseReader(scientist, libraryId);
    }

    public Scientist getById(Long id) {
        return readerRepository.findScientistById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scientist not found with id: " + id));
    }

    public List<Scientist> getAll() {
        return readerRepository.findAllScientists();
    }

    public Scientist update(Long id, Scientist scientistDetails) {
        Scientist scientist = getById(id);
        scientist.setName(scientistDetails.getName());
        scientist.setSurname(scientistDetails.getSurname());
        scientist.setPatronymic(scientistDetails.getPatronymic());
        scientist.setBirthDate(scientistDetails.getBirthDate());
        scientist.setAddress(scientistDetails.getAddress());
        scientist.setPhone(scientistDetails.getPhone());
        scientist.setAcademicDegree(scientistDetails.getAcademicDegree());
        scientist.setWorkplace(scientistDetails.getWorkplace());
        return (Scientist) readerRepository.save(scientist);
    }

    public List<Scientist> getByAcademicDegree(String degree) {
        return readerRepository.findScientistsByAcademicDegree(degree);
    }
}
