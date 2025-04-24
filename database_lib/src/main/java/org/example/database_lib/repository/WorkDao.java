package org.example.database_lib.repository;

import org.example.database_lib.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkDao implements DaoInterface<Work> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Work> rowMapper;

    @Autowired
    public WorkDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Work(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getInt("creation_year"),
                rs.getString("genre")
        );
    }

    @Override
    public int create(Work work) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Work (title, creation_year, genre) " +
                        "VALUES (:title, :creation_year, :genre)")
                .param(work.getTitle())
                .param(work.getCreationYear())
                .param(work.getGenre())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Work> findAll() {
        return jdbcClient.sql("SELECT * FROM Work").query(rowMapper).list();
    }

    @Override
    public Optional<Work> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Work l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Work work) {
        return jdbcClient.sql("UPDATE Work " +
                        "SET title = :title, creation_year = :creation_year, genre = :genre " +
                        "WHERE id = :id")
                .param(work.getId())
                .param(work.getTitle())
                .param(work.getCreationYear())
                .param(work.getGenre())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Work l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
