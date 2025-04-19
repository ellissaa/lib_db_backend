package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResidentService extends ReaderService {
    public ResidentService(ReaderRepository readerRepository,
                           LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Resident create(Resident resident, Long libraryId) {
        return (Resident) createBaseReader(resident, libraryId);
    }

    public Resident getById(Long id) {
        return readerRepository.findResidentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found with id: " + id));
    }

    public List<Resident> getAll() {
        return readerRepository.findAllResidents();
    }

    public Resident update(Long id, Resident residentDetails) {
        Resident resident = getById(id);
        resident.setName(residentDetails.getName());
        resident.setSurname(residentDetails.getSurname());
        resident.setPatronymic(residentDetails.getPatronymic());
        resident.setBirthDate(residentDetails.getBirthDate());
        resident.setAddress(residentDetails.getAddress());
        resident.setPhone(residentDetails.getPhone());
        resident.setOccupation(residentDetails.getOccupation());
        return (Resident) readerRepository.save(resident);
    }
}
