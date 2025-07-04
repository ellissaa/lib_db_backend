package org.example.database_lib.repository;

import org.example.database_lib.model.Librarian;
import org.example.database_lib.model.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibrarianDao implements DaoInterface<Librarian, Long> {
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
    public Librarian create(Librarian librarian) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Librarian (name, surname, patronymic, position, hall_id) " +
                        "VALUES (:name, :surname, :patronymic, :position, :hall_id)")
                .param("name", librarian.getName())
                .param("surname", librarian.getSurname())
                .param("patronymic", librarian.getPatronymic())
                .param("position", librarian.getPosition())
                .param("hall_id", librarian.getHallId())
                .update(keyHolder);
        return librarian;
    }

    @Override
    public List<Librarian> findAll() {
        return jdbcClient.sql("SELECT * FROM Librarian").query(rowMapper).list();
    }

    @Override
    public Optional<Librarian> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Librarian WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public Long countServed(Long id, String start, String end) {
        return jdbcClient.sql("select count(*) from loanjournal j " +
                        "where j.librarian_id = :id " +
                        "and j.loan_date >= cast(:start as date) " +
                        "and j.loan_date <= cast(:end as date)")
                .param("id", id)
                .param("start", start)
                .param("end", end)
                .query(Long.class).single();
    }

    public List<Librarian> findByHall(Long hall) {
        return jdbcClient.sql("select * from librarian l " +
                        "where l.hall_id = :hall " +
                        "order by l.id")
                .param("hall", hall)
                .query(rowMapper).list();
    }

    @Override
    public int update(Librarian librarian) {
        return jdbcClient.sql("UPDATE Librarian " +
                        "SET name = :name, surname = :surname, patronymic = :patronymic, " +
                        "position = :position, hall_id = :hall_id " +
                        "WHERE id = :id")
                .param("name", librarian.getName())
                .param("surname", librarian.getSurname())
                .param("patronymic", librarian.getPatronymic())
                .param("position", librarian.getPosition())
                .param("hall_id", librarian.getHallId())
                .param("id", librarian.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Librarian WHERE id = :id")
                .param("id", id)
                .update();
    }
}
