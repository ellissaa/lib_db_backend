package org.example.database_lib.repository;

import org.example.database_lib.model.AcquisitionJournal;
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
public class AcquisitionJournalDao implements DaoInterface<AcquisitionJournal, Long> {
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
    public AcquisitionJournal create(AcquisitionJournal acquisition_journal) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO AcquisitionJournal (acquisition_date, quantity, operation_type, publication_id) " +
                        "VALUES (:acquisition_date, :quantity, :operation_type, :publication_id)")
                .param("acquisition_date", acquisition_journal.getAcquisitionDate())
                .param("quantity", acquisition_journal.getQuantity())
                .param("operation_type", acquisition_journal.getOperationType())
                .param("publication_id", acquisition_journal.getPublicationId())
                .update(keyHolder);
        return acquisition_journal;
    }

    @Override
    public List<AcquisitionJournal> findAll() {
        return jdbcClient.sql("SELECT * FROM AcquisitionJournal").query(rowMapper).list();
    }

    @Override
    public Optional<AcquisitionJournal> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM AcquisitionJournal WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<AcquisitionJournal> findByPeriod(String start, String end) {
        return jdbcClient.sql("select * from acquisitionjournal a " +
                        "where a.acquisition_date >= cast(:start as date) " +
                        "and a.acquisition_date <= cast(:end as date) " +
                        "order by a.id")
                .param("start", start)
                .param("end", end)
                .query(rowMapper).list();
    }

    @Override
    public int update(AcquisitionJournal acquisition_journal) {
        return jdbcClient.sql("UPDATE AcquisitionJournal " +
                        "SET acquisition_date = :acquisition_date, quantity = :quantity, " +
                        "operation_type = :operation_type, publication_id = :publication_id " +
                        "WHERE id = :id")
                .param("acquisition_date", acquisition_journal.getAcquisitionDate())
                .param("quantity", acquisition_journal.getQuantity())
                .param("operation_type", acquisition_journal.getOperationType())
                .param("publication_id", acquisition_journal.getPublicationId())
                .param("id", acquisition_journal.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from AcquisitionJournal WHERE id = :id")
                .param("id", id)
                .update();
    }
}
