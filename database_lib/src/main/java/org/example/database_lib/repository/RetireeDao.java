package org.example.database_lib.repository;

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
public class RetireeDao implements DaoInterface<Retiree> {
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
    public int create(Retiree retiree) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Retiree (has_benefits) " +
                        "VALUES (:has_benefits)")
                .param(retiree.getHasBenefits())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Retiree> findAll() {
        return jdbcClient.sql("SELECT * FROM Retiree").query(rowMapper).list();
    }

    @Override
    public Optional<Retiree> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Retiree l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Retiree retiree) {
        return jdbcClient.sql("UPDATE Retiree " +
                        "SET has_benefits = :has_benefits " +
                        "WHERE reader_id = :reader_id")
                .param(retiree.getReaderId())
                .param(retiree.getHasBenefits())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Retiree l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
