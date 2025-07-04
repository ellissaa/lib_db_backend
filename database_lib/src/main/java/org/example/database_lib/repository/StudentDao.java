package org.example.database_lib.repository;

import org.example.database_lib.model.Resident;
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
public class StudentDao implements DaoInterface<Student, Long> {
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
    public Student create(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Student (reader_id, university, faculty, student_group) " +
                        "VALUES (:reader_id, :university, :faculty, :student_group)")
                .param("reader_id", student.getReaderId())
                .param("university", student.getUniversity())
                .param("faculty", student.getFaculty())
                .param("student_group", student.getStudentGroup())
                .update(keyHolder);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return jdbcClient.sql("SELECT * FROM Student").query(rowMapper).list();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Student WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Student> findByUniversity(String university) {
        return jdbcClient.sql("select * from student s " +
                        "where lower(s.university) = lower(:university) " +
                        "order by s.reader_id")
                .param("university", university)
                .query(rowMapper).list();
    }

    @Override
    public int update(Student student) {
        return jdbcClient.sql("UPDATE Student " +
                        "SET university = :university, faculty = :faculty, " +
                        "student_group = :student_group " +
                        "WHERE reader_id = :reader_id")
                .param("university", student.getUniversity())
                .param("faculty", student.getFaculty())
                .param("student_group", student.getStudentGroup())
                .param("reader_id", student.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Student WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
