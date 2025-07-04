package org.example.database_lib.repository;

import org.example.database_lib.model.Resident;
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
public class ScientistDao implements DaoInterface<Scientist, Long> {
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
    public Scientist create(Scientist scientist) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Scientist (reader_id, academic_degree, workplace) " +
                        "VALUES (:reader_id, :academic_degree, :workplace)")
                .param("reader_id", scientist.getReaderId())
                .param("academic_degree", scientist.getAcademicDegree())
                .param("workplace", scientist.getWorkplace())
                .update(keyHolder);
        return scientist;
    }

    @Override
    public List<Scientist> findAll() {
        return jdbcClient.sql("SELECT * FROM Scientist").query(rowMapper).list();
    }

    @Override
    public Optional<Scientist> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Scientist WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Scientist> findByWorkplace(String workplace) {
        return jdbcClient.sql("select * from scientist s " +
                        "where lower(s.workplace) = lower(:workplace) " +
                        "order by s.reader_id")
                .param("workplace", workplace)
                .query(rowMapper).list();
    }

    @Override
    public int update(Scientist scientist) {
        return jdbcClient.sql("UPDATE Scientist " +
                        "SET academic_degree = :academic_degree, workplace = :workplace " +
                        "WHERE reader_id = :reader_id")
                .param("academic_degree", scientist.getAcademicDegree())
                .param("workplace", scientist.getWorkplace())
                .param("reader_id", scientist.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Scientist WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
