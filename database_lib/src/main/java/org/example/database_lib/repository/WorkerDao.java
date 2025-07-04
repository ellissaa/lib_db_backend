package org.example.database_lib.repository;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkerDao implements DaoInterface<Worker, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Worker> rowMapper;

    @Autowired
    public WorkerDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Worker(
                rs.getLong("reader_id"),
                rs.getString("profession"),
                rs.getString("organization")
        );
    }

    @Override
    public Worker create(Worker worker) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Worker (reader_id, profession, organization) " +
                        "VALUES (:reader_id, :profession, :organization)")
                .param("reader_id", worker.getReaderId())
                .param("profession", worker.getProfession())
                .param("organization", worker.getOrganization())
                .update(keyHolder);
        return worker;
    }

    @Override
    public List<Worker> findAll() {
        return jdbcClient.sql("SELECT * FROM Worker").query(rowMapper).list();
    }

    @Override
    public Optional<Worker> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Worker WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Worker> findByOrganization(String organization) {
        return jdbcClient.sql("select * from worker w " +
                        "where lower(w.organization) = lower (:organization) " +
                        "order by w.reader_id")
                .param("organization", organization)
                .query(rowMapper).list();
    }

    @Override
    public int update(Worker worker) {
        return jdbcClient.sql("UPDATE Worker " +
                        "SET profession = :profession, organization = :organization " +
                        "WHERE reader_id = :reader_id ")
                .param("profession", worker.getProfession())
                .param("organization", worker.getOrganization())
                .param("reader_id", worker.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Worker WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
