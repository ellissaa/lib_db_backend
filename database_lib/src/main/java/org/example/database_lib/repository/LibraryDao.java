package org.example.database_lib.repository;

import org.example.database_lib.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibraryDao implements DaoInterface<Library> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Library> rowMapper;

    @Autowired
    public LibraryDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Library(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email")
        );
    }

    @Override
    public int create(Library library) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Library (name, address, phone, email) " +
                        "VALUES (:name, :address, :phone, :email)")
                .param(library.getName())
                .param(library.getAddress())
                .param(library.getPhone())
                .param( library.getEmail())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Library> findAll() {
        return jdbcClient.sql("SELECT * FROM Library").query(rowMapper).list();
    }

    @Override
    public Optional<Library> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Library l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Library library) {
        return jdbcClient.sql("UPDATE Library " +
                        "SET name = :name, address = :address, phone = :phone, email = :email " +
                        "WHERE id = :id")
                .param(library.getId())
                .param( library.getName())
                .param( library.getAddress())
                .param( library.getPhone())
                .param( library.getEmail())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Library l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
