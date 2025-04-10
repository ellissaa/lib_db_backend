package org.example.database_lib.repository;

import org.example.database_lib.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/* CRUD-операции для работой с БД */
public interface BookRepository extends JpaRepository<Book, Long> {
}