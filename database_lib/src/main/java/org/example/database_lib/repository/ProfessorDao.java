package org.example.database_lib.repository;

import org.example.database_lib.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorDao implements DaoInterface<Professor> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Professor> rowMapper;

    @Autowired
    public ProfessorDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Professor(
                rs.getLong("reader_id"),
                rs.getString("university"),
                rs.getString("department")
        );
    }

    @Override
    public int create(Professor professor) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Professor (university, department) " +
                        "VALUES (:university, :department)")
                .param(professor.getUniversity())
                .param(professor.getDepartment())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Professor> findAll() {
        return jdbcClient.sql("SELECT * FROM Professor").query(rowMapper).list();
    }

    @Override
    public Optional<Professor> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Professor l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Professor professor) {
        return jdbcClient.sql("UPDATE Professor " +
                        "SET university = :university, department = :department " +
                        "WHERE reader_id = :reader_id")
                .param(professor.getReaderId())
                .param(professor.getUniversity())
                .param(professor.getDepartment())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Professor l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
