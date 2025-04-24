package org.example.database_lib.repository;

import org.example.database_lib.model.LoanJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanJournalDao implements DaoInterface<LoanJournal> {
    private final JdbcClient jdbcClient;
    private final RowMapper<LoanJournal> rowMapper;

    @Autowired
    public LoanJournalDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new LoanJournal(
                rs.getLong("id"),
                rs.getDate("loan_date").toLocalDate(),
                rs.getDate("expected_return_date").toLocalDate(),
                rs.getDate("actual_return_date").toLocalDate(),
                rs.getLong("librarian_id"),
                rs.getLong("copy_id"),
                rs.getLong("reader_id")
        );
    }

    @Override
    public int create(LoanJournal loan_journal) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO LoanJournal (loan_date, expected_return_date, actual_return_date, " +
                        "librarian_id, copy_id, reader_id) " +
                        "VALUES (:loan_date, :expected_return_date, " +
                        ":actual_return_date, :librarian_id, :copy_id, :reader_id)")
                .param(loan_journal.getLoanDate())
                .param(loan_journal.getExpectedReturnDate())
                .param(loan_journal.getActualReturnDate())
                .param(loan_journal.getLibrarianId())
                .param(loan_journal.getCopyId())
                .param(loan_journal.getReaderId())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<LoanJournal> findAll() {
        return jdbcClient.sql("SELECT * FROM LoanJournal").query(rowMapper).list();
    }

    @Override
    public Optional<LoanJournal> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM LoanJournal l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(LoanJournal loan_journal) {
        return jdbcClient.sql("UPDATE LoanJournal " +
                        "SET loan_date = :loan_date, expected_return_date = :expected_return_date, " +
                        "actual_return_date = :actual_return_date, " +
                        "librarian_id = :librarian_id, copy_id = :copy_id, reader_id = :reader_id" +
                        "WHERE id = :id")
                .param(loan_journal.getId())
                .param(loan_journal.getLoanDate())
                .param(loan_journal.getExpectedReturnDate())
                .param(loan_journal.getActualReturnDate())
                .param(loan_journal.getLibrarianId())
                .param(loan_journal.getCopyId())
                .param(loan_journal.getReaderId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from LoanJournal l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
