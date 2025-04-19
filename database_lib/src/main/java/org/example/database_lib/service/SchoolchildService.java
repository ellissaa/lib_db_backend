package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SchoolchildService extends ReaderService {
    public SchoolchildService(ReaderRepository readerRepository,
                              LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Schoolchild create(Schoolchild schoolchild, Long libraryId) {
        return (Schoolchild) createBaseReader(schoolchild, libraryId);
    }

    public Schoolchild getById(Long id) {
        return (Schoolchild) readerRepository.findSchoolchildById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schoolchild not found with id: " + id));
    }

    public List<Schoolchild> getAll() {
        return readerRepository.findAllSchoolchildren();
    }

    public Schoolchild update(Long id, Schoolchild schoolchildDetails) {
        Schoolchild schoolchild = getById(id);
        schoolchild.setName(schoolchildDetails.getName());
        schoolchild.setSurname(schoolchildDetails.getSurname());
        schoolchild.setPatronymic(schoolchildDetails.getPatronymic());
        schoolchild.setBirthDate(schoolchildDetails.getBirthDate());
        schoolchild.setAddress(schoolchildDetails.getAddress());
        schoolchild.setPhone(schoolchildDetails.getPhone());
        schoolchild.setSchool(schoolchildDetails.getSchool());
        schoolchild.setGrade(schoolchildDetails.getGrade());
        return (Schoolchild) readerRepository.save(schoolchild);
    }

    // раскидать на 2
    public List<Schoolchild> getBySchoolAndGrade(String school, String grade) {
        return readerRepository.findSchoolchildrenBySchoolAndGrade(school, grade);
    }
}