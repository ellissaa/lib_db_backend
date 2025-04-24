package org.example.database_lib.repository;

import org.example.database_lib.model.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibrarianDao implements DaoInterface<Librarian> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Librarian> rowMapper;

    @Autowired
    public LibrarianDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Librarian(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getString("position"),
                rs.getLong("hall_id")
        );
    }

    @Override
    public int create(Librarian librarian) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Librarian (name, surname, patronymic, position, hall_id) " +
                        "VALUES (:name, :surname, :patronymic, :position, :hall_id)")
                .param(librarian.getName())
                .param(librarian.getSurname())
                .param(librarian.getPatronymic())
                .param((librarian.getPosition()))
                .param(librarian.getHallId())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Librarian> findAll() {
        return jdbcClient.sql("SELECT * FROM Librarian").query(rowMapper).list();
    }

    @Override
    public Optional<Librarian> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Librarian l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Librarian librarian) {
        return jdbcClient.sql("UPDATE Librarian " +
                        "SET name = :name, surname = :surname, patronymic = :patronymic, " +
                        "position = :position, hall_id = :hall_id " +
                        "WHERE id = :id")
                .param(librarian.getId())
                .param(librarian.getName())
                .param(librarian.getSurname())
                .param(librarian.getPatronymic())
                .param((librarian.getPosition()))
                .param(librarian.getHallId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Librarian l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
