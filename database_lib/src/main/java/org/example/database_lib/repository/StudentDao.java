package org.example.database_lib.repository;

import org.example.database_lib.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDao implements DaoInterface<Student> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Student> rowMapper;

    @Autowired
    public StudentDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Student(
                rs.getLong("reader_id"),
                rs.getString("university"),
                rs.getString("faculty"),
                rs.getString("student_group")
        );
    }

    @Override
    public int create(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Student (university, faculty, student_group) " +
                        "VALUES (:university, :faculty, :student_group)")
                .param(student.getUniversity())
                .param(student.getFaculty())
                .param(student.getStudentGroup())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Student> findAll() {
        return jdbcClient.sql("SELECT * FROM Student").query(rowMapper).list();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Student l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Student student) {
        return jdbcClient.sql("UPDATE Student " +
                        "SET university = :university, faculty = :faculty, " +
                        "student_group = :student_group " +
                        "WHERE reader_id = :reader_id")
                .param(student.getReaderId())
                .param(student.getUniversity())
                .param(student.getFaculty())
                .param(student.getStudentGroup())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Student l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
