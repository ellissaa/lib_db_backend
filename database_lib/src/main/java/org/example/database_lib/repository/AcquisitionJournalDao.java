package org.example.database_lib.repository;

import org.example.database_lib.model.AcquisitionJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AcquisitionJournalDao implements DaoInterface<AcquisitionJournal> {
    private final JdbcClient jdbcClient;
    private final RowMapper<AcquisitionJournal> rowMapper;

    @Autowired
    public AcquisitionJournalDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new AcquisitionJournal(
                rs.getLong("id"),
                rs.getDate("acquisition_date").toLocalDate(),
                rs.getInt("quantity"),
                rs.getString("operation_type"),
                rs.getLong("publication_id")
        );
    }

    @Override
    public int create(AcquisitionJournal acquisition_journal) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO AcquisitionJournal (acquisition_date, quantity, operation_type, publication_id) " +
                        "VALUES (:acquisition_date, :quantity, :operation_type, :publication_id)")
                .param(acquisition_journal.getAcquisitionDate())
                .param(acquisition_journal.getQuantity())
                .param(acquisition_journal.getOperationType())
                .param( acquisition_journal.getPublicationId())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<AcquisitionJournal> findAll() {
        return jdbcClient.sql("SELECT * FROM AcquisitionJournal").query(rowMapper).list();
    }

    @Override
    public Optional<AcquisitionJournal> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM AcquisitionJournal l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(AcquisitionJournal acquisition_journal) {
        return jdbcClient.sql("UPDATE AcquisitionJournal " +
                        "SET acquisition_date = :acquisition_date, quantity = :quantity, " +
                        "operation_type = :operation_type, publication_id = :publication_id " +
                        "WHERE id = :id")
                .param(acquisition_journal.getId())
                .param(acquisition_journal.getAcquisitionDate())
                .param(acquisition_journal.getQuantity())
                .param(acquisition_journal.getOperationType())
                .param( acquisition_journal.getPublicationId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from AcquisitionJournal l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
