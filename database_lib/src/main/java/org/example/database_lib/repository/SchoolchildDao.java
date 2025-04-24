package org.example.database_lib.repository;

import org.example.database_lib.model.Schoolchild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SchoolchildDao implements DaoInterface<Schoolchild> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Schoolchild> rowMapper;

    @Autowired
    public SchoolchildDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Schoolchild(
                rs.getLong("reader_id"),
                rs.getString("school"),
                rs.getString("grade")
        );
    }

    @Override
    public int create(Schoolchild schoolchild) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Schoolchild (school, grade) " +
                        "VALUES (:school, :grade)")
                .param(schoolchild.getSchool())
                .param(schoolchild.getGrade())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Schoolchild> findAll() {
        return jdbcClient.sql("SELECT * FROM Schoolchild").query(rowMapper).list();
    }

    @Override
    public Optional<Schoolchild> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Schoolchild l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Schoolchild schoolchild) {
        return jdbcClient.sql("UPDATE Schoolchild " +
                        "SET school = :school, grade = :grade " +
                        "WHERE reader_id = :reader_id")
                .param(schoolchild.getReaderId())
                .param(schoolchild.getSchool())
                .param(schoolchild.getGrade())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Schoolchild l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
