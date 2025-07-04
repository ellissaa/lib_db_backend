package org.example.database_lib.repository;

import org.example.database_lib.model.PublicationWork;
import org.example.database_lib.model.PublicationWorkId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublicationWorkDao implements DaoInterface<PublicationWork, PublicationWorkId> {
    private final JdbcClient jdbcClient;
    private final RowMapper<PublicationWork> rowMapper;

    @Autowired
    public PublicationWorkDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new PublicationWork(
                    rs.getLong("publication_id"),
                    rs.getLong("work_id")
        );
    }

    @Override
    public PublicationWork create(PublicationWork entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO publication_work (publication_id, work_id) " +
                        "VALUES (:publication_id, :work_id)")
                .param("publication_id", entity.getPublicationId())
                .param("work_id", entity.getWorkId())
                .update(keyHolder);
        return entity;
    }

    @Override
    public List<PublicationWork> findAll() {
        return jdbcClient.sql("SELECT * FROM publication_work").query(rowMapper).list();
    }

    @Override
    public Optional<PublicationWork> findById(PublicationWorkId publicationWorkId) {
        return jdbcClient.sql("SELECT * FROM publication_work " +
                        "WHERE publication_id = :publication_id AND work_id = :work_id")
                .param("publication_id", publicationWorkId.getPublicationId())
                .param("work_id", publicationWorkId.getWorkId())
                .query(rowMapper).optional();
    }

    @Override
    public int update(PublicationWork entity) {
        return 0;
    }

    @Override
    public int delete(PublicationWorkId publicationWorkId) {
        return jdbcClient.sql("DELETE from publication_work " +
                        "WHERE publication_id = :publication_id AND work_id = :work_id")
                .param("publication_id", publicationWorkId.getPublicationId())
                .param("work_id", publicationWorkId.getWorkId())
                .update();
    }
}
