package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WorkService {
    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;
    private final WorkAuthorRepository workAuthorRepository;

    public WorkService(WorkRepository workRepository,
                       AuthorRepository authorRepository,
                       WorkAuthorRepository workAuthorRepository) {
        this.workRepository = workRepository;
        this.authorRepository = authorRepository;
        this.workAuthorRepository = workAuthorRepository;
    }

    public Work create(Work work, List<Long> authorIds) {
        Work savedWork = workRepository.save(work);
        authorIds.forEach(authorId -> {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            WorkAuthor workAuthor = new WorkAuthor();
            workAuthor.setWork(savedWork);
            workAuthor.setAuthor(author);
            workAuthorRepository.save(workAuthor);
        });
        return savedWork;
    }

    public Work getById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found"));
    }

    public List<Work> getAll() {
        return workRepository.findAll();
    }

    public Work update(Long id, Work workDetails) {
        Work work = getById(id);
        work.setTitle(workDetails.getTitle());
        work.setCreationYear(workDetails.getCreationYear());
        work.setGenre(workDetails.getGenre());
        return workRepository.save(work);
    }

    public void delete(Long id) {
        Work work = getById(id);
        workRepository.delete(work);
    }

    public List<Work> getWorksByAuthor(Long authorId) {
        return workRepository.findByAuthorsId(authorId);
    }
}