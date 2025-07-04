package org.example.database_lib.repository;

import org.example.database_lib.model.Professor;
import org.example.database_lib.model.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorDao implements DaoInterface<Professor, Long> {
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
    public Professor create(Professor professor) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Professor (reader_id, university, department) " +
                        "VALUES (:reader_id, :university, :department)")
                .param("reader_id", professor.getReaderId())
                .param("university", professor.getUniversity())
                .param("department", professor.getDepartment())
                .update(keyHolder);
        return professor;
    }

    @Override
    public List<Professor> findAll() {
        return jdbcClient.sql("SELECT * FROM Professor").query(rowMapper).list();
    }

    @Override
    public Optional<Professor> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Professor WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Professor> findByUniversity(String university) {
        return jdbcClient.sql("select * from professor p " +
                        "where lower(p.university) = lower(:university) " +
                        "order by p.reader_id")
                .param("university", university)
                .query(rowMapper).list();
    }

    @Override
    public int update(Professor professor) {
        return jdbcClient.sql("UPDATE Professor " +
                        "SET university = :university, department = :department " +
                        "WHERE reader_id = :reader_id")
                .param("university", professor.getUniversity())
                .param("department", professor.getDepartment())
                .param("reader_id", professor.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Professor WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
