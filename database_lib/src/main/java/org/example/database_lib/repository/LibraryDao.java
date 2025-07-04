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
public class LibraryDao implements DaoInterface<Library, Long> {
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
    public Library create(Library library) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Library (name, address, phone, email) " +
                        "VALUES (:name, :address, :phone, :email)")
                .param("name", library.getName())
                .param("address", library.getAddress())
                .param("phone", library.getPhone())
                .param("email", library.getEmail())
                .update(keyHolder);
        return library;
    }

    @Override
    public List<Library> findAll() {
        return jdbcClient.sql("SELECT * FROM Library").query(rowMapper).list();
    }

    @Override
    public Optional<Library> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Library WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Library library) {
        return jdbcClient.sql("UPDATE Library " +
                        "SET name = :name, address = :address, phone = :phone, email = :email " +
                        "WHERE id = :id")
                .param("name", library.getName())
                .param("address", library.getAddress())
                .param("phone", library.getPhone())
                .param("email", library.getEmail())
                .param("id", library.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Library WHERE id = :id")
                .param("id", id)
                .update();
    }
}
