package org.example.database_lib.repository;

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
public class WorkerDao implements DaoInterface<Worker> {
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
    public int create(Worker worker) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Worker (profession, organization) " +
                        "VALUES (:profession, :organization)")
                .param(worker.getProfession())
                .param(worker.getOrganization())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Worker> findAll() {
        return jdbcClient.sql("SELECT * FROM Worker").query(rowMapper).list();
    }

    @Override
    public Optional<Worker> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Worker l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Worker worker) {
        return jdbcClient.sql("UPDATE Worker " +
                        "SET profession = :profession, organization = :organization " +
                        "WHERE reader_id = :reader_id")
                .param(worker.getReaderId())
                .param(worker.getProfession())
                .param(worker.getOrganization())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Worker l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
