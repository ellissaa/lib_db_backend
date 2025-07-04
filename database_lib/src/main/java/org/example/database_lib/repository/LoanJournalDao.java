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
public class LoanJournalDao implements DaoInterface<LoanJournal, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<LoanJournal> rowMapper;

    @Autowired
    public LoanJournalDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> {
            var loanDate = rs.getDate("loan_date");
            var expectedReturnDate = rs.getDate("expected_return_date");
            var actualReturnDate = rs.getDate("actual_return_date");
            return new LoanJournal(
                    rs.getLong("id"),
                    loanDate == null ? null : loanDate.toLocalDate(),
                    expectedReturnDate == null ? null : expectedReturnDate.toLocalDate(),
                    actualReturnDate == null ? null : actualReturnDate.toLocalDate(),
                    rs.getLong("librarian_id"),
                    rs.getLong("copy_id"),
                    rs.getLong("reader_id")
            );
        };
    }

    @Override
    public LoanJournal create(LoanJournal loan_journal) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO LoanJournal (loan_date, expected_return_date, actual_return_date, " +
                        "librarian_id, copy_id, reader_id) " +
                        "VALUES (:loan_date, :expected_return_date, " +
                        ":actual_return_date, :librarian_id, :copy_id, :reader_id)")
                .param("loan_date", loan_journal.getLoanDate())
                .param("expected_return_date", loan_journal.getExpectedReturnDate())
                .param("actual_return_date", loan_journal.getActualReturnDate())
                .param("librarian_id", loan_journal.getLibrarianId())
                .param("copy_id", loan_journal.getCopyId())
                .param("reader_id", loan_journal.getReaderId())
                .update(keyHolder);
        return loan_journal;
    }

    @Override
    public List<LoanJournal> findAll() {
        return jdbcClient.sql("SELECT * FROM LoanJournal").query(rowMapper).list();
    }

    @Override
    public Optional<LoanJournal> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM LoanJournal WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(LoanJournal loan_journal) {
        return jdbcClient.sql("UPDATE LoanJournal " +
                        "SET loan_date = :loan_date, expected_return_date = :expected_return_date, " +
                        "actual_return_date = :actual_return_date, " +
                        "librarian_id = :librarian_id, copy_id = :copy_id, reader_id = :reader_id " +
                        "WHERE id = :id")
                .param("loan_date", loan_journal.getLoanDate())
                .param("expected_return_date", loan_journal.getExpectedReturnDate())
                .param("actual_return_date", loan_journal.getActualReturnDate())
                .param("librarian_id", loan_journal.getLibrarianId())
                .param("copy_id", loan_journal.getCopyId())
                .param("reader_id", loan_journal.getReaderId())
                .param("id", loan_journal.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from LoanJournal WHERE id = :id")
                .param("id", id)
                .update();
    }
}
