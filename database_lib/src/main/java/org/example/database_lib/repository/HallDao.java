package org.example.database_lib.repository;

import org.example.database_lib.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HallDao implements DaoInterface<Hall, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Hall> rowMapper;

    @Autowired
    public HallDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Hall(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getLong("library_id")
        );
    }

    @Override
    public Hall create(Hall hall) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Hall (name, description, library_id) " +
                        "VALUES (:name, :description, :library_id)")
                .param("name", hall.getName())
                .param("description", hall.getDescription())
                .param("library_id", hall.getLibraryId())
                .update(keyHolder);
        return hall;
    }

    @Override
    public List<Hall> findAll() {
        return jdbcClient.sql("SELECT * FROM Hall").query(rowMapper).list();
    }

    @Override
    public Optional<Hall> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Hall WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Hall hall) {
        return jdbcClient.sql("UPDATE Hall " +
                        "SET name = :name, description = :description, library_id = :library_id " +
                        "WHERE id = :id")
                .param("name", hall.getName())
                .param("description", hall.getDescription())
                .param("library_id", hall.getLibraryId())
                .param("id", hall.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Hall WHERE id = :id")
                .param("id", id)
                .update();
    }
}
