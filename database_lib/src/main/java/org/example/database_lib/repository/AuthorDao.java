package org.example.database_lib.repository;

import org.example.database_lib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDao implements DaoInterface<Author, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Author> rowMapper;

    @Autowired
    public AuthorDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Author(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getString("country"),
                rs.getInt("birth_year")
        );
    }

    @Override
    public Author create(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Author (name, surname, patronymic, country, birth_year) " +
                        "VALUES (:name, :surname, :patronymic, :country, :birth_year)")
                .param("name", author.getName())
                .param("surname", author.getSurname())
                .param("patronymic", author.getPatronymic())
                .param("country", author.getCountry())
                .param("birth_year", author.getBirthYear())
                .update(keyHolder);
        return author;
    }

    @Override
    public List<Author> findAll() {
        return jdbcClient.sql("SELECT * FROM Author").query(rowMapper).list();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Author WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Author author) {
        return jdbcClient.sql("UPDATE Author " +
                        "SET name = :name, surname = :surname, patronymic = :patronymic, " +
                        "country = :country, birth_year = :birth_year " +
                        "WHERE id = :id")
                .param("name", author.getName())
                .param("surname", author.getSurname())
                .param("patronymic", author.getPatronymic())
                .param("country", author.getCountry())
                .param("birth_year", author.getBirthYear())
                .param("id", author.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Author WHERE id = :id")
                .param("id", id)
                .update();
    }
}
