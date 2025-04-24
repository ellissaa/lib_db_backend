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
public class HallDao implements DaoInterface<Hall> {
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
    public int create(Hall hall) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Hall (name, description, library_id) " +
                        "VALUES (:name, :description, :library_id)")
                .param(hall.getName())
                .param(hall.getDescription())
                .param(hall.getLibraryId())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Hall> findAll() {
        return jdbcClient.sql("SELECT * FROM Hall").query(rowMapper).list();
    }

    @Override
    public Optional<Hall> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Hall l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Hall hall) {
        return jdbcClient.sql("UPDATE Hall " +
                        "SET name = :name, description = :description, library_id = :library_id" +
                        "WHERE id = :id")
                .param(hall.getId())
                .param(hall.getName())
                .param(hall.getDescription())
                .param(hall.getLibraryId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Hall l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
