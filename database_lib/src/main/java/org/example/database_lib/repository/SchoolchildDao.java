package org.example.database_lib.repository;

import org.example.database_lib.model.Resident;
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
public class SchoolchildDao implements DaoInterface<Schoolchild, Long> {
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
    public Schoolchild create(Schoolchild schoolchild) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Schoolchild (reader_id, school, grade) " +
                        "VALUES (:reader_id, :school, :grade)")
                .param("reader_id", schoolchild.getReaderId())
                .param("school", schoolchild.getSchool())
                .param("grade", schoolchild.getGrade())
                .update(keyHolder);
        return schoolchild;
    }

    @Override
    public List<Schoolchild> findAll() {
        return jdbcClient.sql("SELECT * FROM Schoolchild").query(rowMapper).list();
    }

    @Override
    public Optional<Schoolchild> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Schoolchild WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Schoolchild> findBySchool(String school) {
        return jdbcClient.sql("select * from schoolchild s " +
                        "where lower(s.school) = lower(:school) " +
                        "order by s.reader_id")
                .param("school", school)
                .query(rowMapper).list();
    }

    @Override
    public int update(Schoolchild schoolchild) {
        return jdbcClient.sql("UPDATE Schoolchild " +
                        "SET school = :school, grade = :grade " +
                        "WHERE reader_id = :reader_id")
                .param("school", schoolchild.getSchool())
                .param("grade", schoolchild.getGrade())
                .param("reader_id", schoolchild.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Schoolchild WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
