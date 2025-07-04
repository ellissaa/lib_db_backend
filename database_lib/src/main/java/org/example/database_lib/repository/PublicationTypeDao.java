package org.example.database_lib.repository;

import org.example.database_lib.model.PublicationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublicationTypeDao implements DaoInterface<PublicationType, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<PublicationType> rowMapper;

    @Autowired
    public PublicationTypeDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new PublicationType(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    @Override
    public PublicationType create(PublicationType publication_type) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO PublicationType (name) " +
                        "VALUES (:name)")
                .param("name", publication_type.getName())
                .update(keyHolder);
        return publication_type;
    }

    @Override
    public List<PublicationType> findAll() {
        return jdbcClient.sql("SELECT * FROM PublicationType").query(rowMapper).list();
    }

    @Override
    public Optional<PublicationType> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM PublicationType WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(PublicationType publication_type) {
        return jdbcClient.sql("UPDATE PublicationType " +
                        "SET name = :name " +
                        "WHERE id = :id")
                .param("name", publication_type.getName())
                .param("id", publication_type.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from PublicationType WHERE id = :id")
                .param("id", id)
                .update();
    }
}
