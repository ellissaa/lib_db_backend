package org.example.database_lib.repository;

import org.example.database_lib.model.WorkAuthor;
import org.example.database_lib.model.WorkAuthorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkAuthorDao implements DaoInterface<WorkAuthor, WorkAuthorId> {
    private final JdbcClient jdbcClient;
    private final RowMapper<WorkAuthor> rowMapper;

    @Autowired
    public WorkAuthorDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new WorkAuthor(
                    rs.getLong("work_id"),
                    rs.getLong("author_id")
        );
    }

    @Override
    public WorkAuthor create(WorkAuthor entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO work_author(work_id, author_id) " +
                        "VALUES (:work_id, :author_id)")
                .param("work_id", entity.getWorkId())
                .param("author_id", entity.getAuthorId())
                .update(keyHolder);
        return entity;
    }

    @Override
    public List<WorkAuthor> findAll() {
        return jdbcClient.sql("SELECT * FROM work_author").query(rowMapper).list();
    }

    @Override
    public Optional<WorkAuthor> findById(WorkAuthorId workAuthorId) {
        return jdbcClient.sql("SELECT * FROM work_author " +
                        "WHERE work_id = :work_id AND author_id = :author_id")
                .param("work_id", workAuthorId.getWorkId())
                .param("author_id", workAuthorId.getAuthorId())
                .query(rowMapper).optional();
    }

    @Override
    public int update(WorkAuthor entity) {
        return 0;
    }

    @Override
    public int delete(WorkAuthorId workAuthorId) {
        return jdbcClient.sql("DELETE from work_author " +
                        "WHERE work_id = :work_id AND author_id = :author_id")
                .param("work_id", workAuthorId.getWorkId())
                .param("author_id", workAuthorId.getAuthorId())
                .update();
    }
}
