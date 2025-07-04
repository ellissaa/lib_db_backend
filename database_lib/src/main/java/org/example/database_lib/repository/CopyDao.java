package org.example.database_lib.repository;

import org.example.database_lib.model.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CopyDao implements DaoInterface<Copy, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Copy> rowMapper;

    @Autowired
    public CopyDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Copy(
                rs.getLong("id"),
                rs.getLong("publication_id")
        );
    }

    @Override
    public Copy create(Copy copy) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Copy (publication_id) " +
                        "VALUES (:publication_id)")
                .param("publication_id", copy.getPublicationId())
                .update(keyHolder);
        return copy;
    }

    @Override
    public List<Copy> findAll() {
        return jdbcClient.sql("SELECT * FROM Copy").query(rowMapper).list();
    }

    @Override
    public Optional<Copy> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Copy WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Copy copy) {
        return jdbcClient.sql("UPDATE Copy " +
                        "SET publication_id = :publication_id " +
                        "WHERE id = :id")
                .param("publication_id", copy.getPublicationId())
                .param("id", copy.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Copy WHERE id = :id")
                .param("id", id)
                .update();
    }
}
