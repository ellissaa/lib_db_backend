package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public List<Author> getByCountry(String country) {
        return authorRepository.findByCountry(country);
    }

    public Author update(Long id, Author authorDetails) {
        Author author = getById(id);
        author.setName(authorDetails.getName());
        author.setSurname(authorDetails.getSurname());
        author.setPatronymic(authorDetails.getPatronymic());
        author.setCountry(authorDetails.getCountry());
        author.setBirthYear(authorDetails.getBirthYear());
        return authorRepository.save(author);
    }

    public void delete(Long id) {
        Author author = getById(id);
        authorRepository.delete(author);
    }
}