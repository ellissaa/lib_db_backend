package org.example.database_lib.repository;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Retiree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RetireeDao implements DaoInterface<Retiree, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Retiree> rowMapper;

    @Autowired
    public RetireeDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Retiree(
                rs.getLong("reader_id"),
                rs.getBoolean("has_benefits")
        );
    }

    @Override
    public Retiree create(Retiree retiree) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Retiree (reader_id, has_benefits) " +
                        "VALUES (:reader_id, :has_benefits)")
                .param("reader_id", retiree.getReaderId())
                .param("has_benefits", retiree.getHasBenefits())
                .update(keyHolder);
        return retiree;
    }

    @Override
    public List<Retiree> findAll() {
        return jdbcClient.sql("SELECT * FROM Retiree").query(rowMapper).list();
    }

    @Override
    public Optional<Retiree> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Retiree WHERE reader_id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Retiree> findByBenefits(Boolean benefits) {
        return jdbcClient.sql("select * from retiree r " +
                        "where r.has_benefits = :benefits " +
                        "order by r.reader_id")
                .param("benefits", benefits)
                .query(rowMapper).list();
    }

    @Override
    public int update(Retiree retiree) {
        return jdbcClient.sql("UPDATE Retiree " +
                        "SET has_benefits = :has_benefits " +
                        "WHERE reader_id = :reader_id")
                .param("has_benefits", retiree.getHasBenefits())
                .param("reader_id", retiree.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Retiree WHERE reader_id = :id")
                .param("id", id)
                .update();
    }
}
