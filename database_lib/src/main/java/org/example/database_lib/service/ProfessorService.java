package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProfessorService extends ReaderService {
    public ProfessorService(ReaderRepository readerRepository,
                          LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Professor create(Professor professor, Long libraryId) {
        return (Professor) createBaseReader(professor, libraryId);
    }

    public Professor getById(Long id) {
        return (Professor) readerRepository.findProfessorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
    }

    public List<Professor> getAll() {
        return readerRepository.findAllProfessors();
    }

    public Professor update(Long id, Professor professorDetails) {
        Professor professor = getById(id);
        professor.setName(professorDetails.getName());
        professor.setSurname(professorDetails.getSurname());
        professor.setPatronymic(professorDetails.getPatronymic());
        professor.setBirthDate(professorDetails.getBirthDate());
        professor.setAddress(professorDetails.getAddress());
        professor.setPhone(professorDetails.getPhone());
        professor.setUniversity(professorDetails.getUniversity());
        professor.setDepartment(professorDetails.getDepartment());
        return (Professor) readerRepository.save(professor);
    }

    public List<Professor> getByUniversity(String university) {
        return readerRepository.findProfessorsByUniversity(university);
    }

    public List<Professor> getByDepartment(String department) {
        return readerRepository.findProfessorsByDepartment(department);
    }
}
