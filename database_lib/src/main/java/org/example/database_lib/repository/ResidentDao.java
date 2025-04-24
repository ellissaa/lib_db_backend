package org.example.database_lib.repository;

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
public class ResidentDao implements DaoInterface<Resident> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Resident> rowMapper;

    @Autowired
    public ResidentDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Resident(
                rs.getLong("reader_id"),
                rs.getString("occupation")
        );
    }

    @Override
    public int create(Resident resident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Resident (occupation) " +
                        "VALUES (:occupation)")
                .param(resident.getOccupation())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Resident> findAll() {
        return jdbcClient.sql("SELECT * FROM Resident").query(rowMapper).list();
    }

    @Override
    public Optional<Resident> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Resident l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Resident resident) {
        return jdbcClient.sql("UPDATE Resident " +
                        "SET occupation = :occupation " +
                        "WHERE reader_id = :reader_id")
                .param(resident.getReaderId())
                .param(resident.getOccupation())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Resident l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
