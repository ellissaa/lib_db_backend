package org.example.database_lib.repository;

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
public class ResidentDao implements DaoInterface<Resident, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Resident> rowMapper;

    @Autowired
    public ResidentDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Resident(
                rs.getLong("reader_id"),
                rs.getString("occupation")
        );
    }

    @Override
    public Resident create(Resident resident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Resident (reader_id, occupation) " +
                        "VALUES (:reader_id, :occupation)")
                .param("reader_id", resident.getReaderId())
                .param("occupation", resident.getOccupation())
                .update(keyHolder);
        return resident;
    }

    @Override
    public List<Resident> findAll() {
        return jdbcClient.sql("SELECT * FROM Resident").query(rowMapper).list();
    }

    @Override
    public Optional<Resident> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Resident WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Resident> findByOccupation(String occupation) {
        return jdbcClient.sql("select * from resident r " +
                        "where lower(r.occupation) = lower(:occupation) " +
                        "order by r.reader_id")
                .param("occupation", occupation)
                .query(rowMapper).list();
    }

    @Override
    public int update(Resident resident) {
        return jdbcClient.sql("UPDATE Resident " +
                        "SET occupation = :occupation " +
                        "WHERE reader_id = :reader_id")
                .param("occupation", resident.getOccupation())
                .param("reader_id", resident.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Resident WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
