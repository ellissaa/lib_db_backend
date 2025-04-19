package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WorkAuthorService {
    private final WorkAuthorRepository workAuthorRepository;
    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;

    public WorkAuthorService(WorkAuthorRepository workAuthorRepository,
                             WorkRepository workRepository,
                             AuthorRepository authorRepository) {
        this.workAuthorRepository = workAuthorRepository;
        this.workRepository = workRepository;
        this.authorRepository = authorRepository;
    }

    public WorkAuthor create(Long workId, Long authorId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found with id: " + workId));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

        WorkAuthor workAuthor = new WorkAuthor();
        workAuthor.setWork(work);
        workAuthor.setAuthor(author);

        return workAuthorRepository.save(workAuthor);
    }

    public WorkAuthor getById(WorkAuthorId id) {
        return workAuthorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Work-Author relationship not found for workId: " +
                                id.getWorkId() + " and authorId: " + id.getAuthorId()));
    }

    public List<WorkAuthor> getAll() {
        return workAuthorRepository.findAll();
    }

    public List<Work> getWorksByAuthor(Long authorId) {
        return workAuthorRepository.findWorksByAuthorId(authorId);
    }

    public List<Author> getAuthorsByWork(Long workId) {
        return workAuthorRepository.findAuthorsByWorkId(workId);
    }

    public void delete(WorkAuthorId id) {
        WorkAuthor workAuthor = getById(id);
        workAuthorRepository.delete(workAuthor);
    }

    public void deleteByWorkAndAuthor(Long workId, Long authorId) {
        WorkAuthorId id = new WorkAuthorId(workId, authorId);
        delete(id);
    }
}
