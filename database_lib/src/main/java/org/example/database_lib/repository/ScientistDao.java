package org.example.database_lib.repository;

import org.example.database_lib.model.Scientist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScientistDao implements DaoInterface<Scientist> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Scientist> rowMapper;

    @Autowired
    public ScientistDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Scientist(
                rs.getLong("reader_id"),
                rs.getString("academic_degree"),
                rs.getString("workplace")
        );
    }

    @Override
    public int create(Scientist scientist) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Scientist (academic_degree, workplace) " +
                        "VALUES (:academic_degree, :workplace)")
                .param(scientist.getAcademicDegree())
                .param(scientist.getWorkplace())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Scientist> findAll() {
        return jdbcClient.sql("SELECT * FROM Scientist").query(rowMapper).list();
    }

    @Override
    public Optional<Scientist> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Scientist l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Scientist scientist) {
        return jdbcClient.sql("UPDATE Scientist " +
                        "SET academic_degree = :academic_degree, workplace = :workplace " +
                        "WHERE reader_id = :reader_id")
                .param(scientist.getReaderId())
                .param(scientist.getAcademicDegree())
                .param(scientist.getWorkplace())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Scientist l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
