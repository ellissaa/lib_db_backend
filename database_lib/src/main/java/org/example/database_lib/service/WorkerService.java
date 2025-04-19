package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WorkerService extends ReaderService {
    public WorkerService(ReaderRepository readerRepository,
                         LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Worker create(Worker worker, Long libraryId) {
        return (Worker) createBaseReader(worker, libraryId);
    }

    public Worker getById(Long id) {
        return (Worker) readerRepository.findWorkerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + id));
    }

    public List<Worker> getAll() {
        return readerRepository.findAllWorkers();
    }

    public Worker update(Long id, Worker workerDetails) {
        Worker worker = getById(id);
        worker.setName(workerDetails.getName());
        worker.setSurname(workerDetails.getSurname());
        worker.setPatronymic(workerDetails.getPatronymic());
        worker.setBirthDate(workerDetails.getBirthDate());
        worker.setAddress(workerDetails.getAddress());
        worker.setPhone(workerDetails.getPhone());
        worker.setProfession(workerDetails.getProfession());
        worker.setOrganization(workerDetails.getOrganization());
        return (Worker) readerRepository.save(worker);
    }

    public List<Worker> getByProfession(String profession) {
        return readerRepository.findWorkersByProfession(profession);
    }

    public List<Worker> getByOrganization(String organization) {
        return readerRepository.findWorkersByOrganization(organization);
    }
}
