package org.example.database_lib.repository;

import org.example.database_lib.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReaderDao implements DaoInterface<Reader> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Reader> rowMapper;

    @Autowired
    public ReaderDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Reader(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getLong("library_id")
        );
    }

    @Override
    public int create(Reader reader) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Reader (name, surname, patronymic, " +
                        "birth_date, address, phone, library_id) " +
                        "VALUES (:name, :surname, :patronymic, :birth_date, " +
                        ":address, :phone, :library_id)")
                .param(reader.getName())
                .param(reader.getSurname())
                .param(reader.getPatronymic())
                .param(reader.getBirthDate())
                .param( reader.getAddress())
                .param(reader.getPhone())
                .param(reader.getLibraryId())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Reader> findAll() {
        return jdbcClient.sql("SELECT * FROM Reader").query(rowMapper).list();
    }

    @Override
    public Optional<Reader> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Reader l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Reader reader) {
        return jdbcClient.sql("UPDATE Reader " +
                        "SET name = :name, surname = :surname, patronymic = :patronymic, " +
                        "birth_date = :birth_date, address = :address, " +
                        "phone = :phone, library_id = :library_id " +
                        "WHERE id = :id")
                .param(reader.getId())
                .param(reader.getName())
                .param(reader.getSurname())
                .param(reader.getPatronymic())
                .param(reader.getBirthDate())
                .param( reader.getAddress())
                .param(reader.getPhone())
                .param(reader.getLibraryId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Reader l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
