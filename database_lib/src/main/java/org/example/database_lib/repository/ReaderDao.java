package org.example.database_lib.repository;

import org.example.database_lib.model.Reader;
import org.example.database_lib.model.ReaderPublication;
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
public class ReaderDao implements DaoInterface<Reader, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Reader> rowMapper;
    private final RowMapper<ReaderPublication> rowMapperPublication;

    @Autowired
    public ReaderDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Reader(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getLong("library_id")
        );
        this.rowMapperPublication = (rs, rowNum) -> new ReaderPublication(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getLong("library_id"),
                rs.getString("title")
        );
    }

    @Override
    public Reader create(Reader reader) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Reader (name, surname, patronymic, " +
                        "birth_date, address, phone, library_id) " +
                        "VALUES (:name, :surname, :patronymic, :birth_date, " +
                        ":address, :phone, :library_id)")
                .param("name", reader.getName())
                .param("surname", reader.getSurname())
                .param("patronymic", reader.getPatronymic())
                .param("birth_date", reader.getBirthDate())
                .param("address", reader.getAddress())
                .param("phone", reader.getPhone())
                .param("library_id", reader.getLibraryId())
                .update(keyHolder);
        return reader;
    }

    @Override
    public List<Reader> findAll() {
        return jdbcClient.sql("SELECT * FROM Reader").query(rowMapper).list();
    }

    @Override
    public Optional<Reader> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Reader WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Reader> findByWork(String work) {
        return jdbcClient.sql("select distinct r.* from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "join copy c on j.copy_id = c.id " +
                        "join publication p on c.publication_id = p.id " +
                        "join publication_work pw on p.id = pw.publication_id " +
                        "join work w on pw.work_id = w.id " +
                        "where j.actual_return_date is null " +
                        "and lower(w.title) = lower(:work) " +
                        "order by r.id")
                .param("work", work)
                .query(rowMapper).list();
    }

    public List<Reader> findByPublication(String publication) {
        return jdbcClient.sql("select distinct r.* from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "join copy c on j.copy_id = c.id " +
                        "join publication p on c.publication_id = p.id " +
                        "where j.actual_return_date is null " +
                        "and lower(p.title) = lower(:publication) " +
                        "order by r.id")
                .param("publication", publication)
                .query(rowMapper).list();
    }

    public List<ReaderPublication> findGotWork(String start, String end, String work) {
        return jdbcClient.sql("select r.*, p.title from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "join copy c on j.copy_id = c.id " +
                        "join publication p on c.publication_id = p.id " +
                        "join publication_work pw on p.id = pw.publication_id " +
                        "join work w on pw.work_id = w.id " +
                        "where j.loan_date >= cast(:start as date) " +
                        "and j.loan_date <= cast(:end as date) " +
                        "and lower(w.title) = lower(:work) " +
                        "order by r.id")
                .param("start", start)
                .param("end", end)
                .param("work", work)
                .query(rowMapperPublication).list();
    }

    public List<Reader> findByLibrarian(Long librarian, String start, String end) {
        return jdbcClient.sql("select distinct r.* from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "where j.librarian_id = :librarian " +
                        "and j.loan_date >= cast(:start as date) " +
                        "and j.loan_date <= cast(:end as date) " +
                        "order by r.id")
                .param("librarian", librarian)
                .param("start", start)
                .param("end", end)
                .query(rowMapper).list();
    }

    public List<Reader> findOverdue() {
        return jdbcClient.sql("select distinct r.* from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "where j.actual_return_date is null " +
                        "and j.expected_return_date < current_date " +
                        "order by r.id")
                .query(rowMapper).list();
    }

    public List<Reader> findNotVisited(String start, String end) {
        return jdbcClient.sql("select distinct r.* from loanjournal j " +
                        "join reader r on j.reader_id = r.id " +
                        "where j.loan_date < cast(:start as date) " +
                        "or j.loan_date > cast(:end as date) " +
                        "order by r.id")
                .param("start", start)
                .param("end", end)
                .query(rowMapper).list();
    }

    @Override
    public int update(Reader reader) {
        return jdbcClient.sql("UPDATE Reader " +
                        "SET name = :name, surname = :surname, patronymic = :patronymic, " +
                        "birth_date = :birth_date, address = :address, " +
                        "phone = :phone, library_id = :library_id " +
                        "WHERE id = :id")
                .param("name", reader.getName())
                .param("surname", reader.getSurname())
                .param("patronymic", reader.getPatronymic())
                .param("birth_date", reader.getBirthDate())
                .param("address", reader.getAddress())
                .param("phone", reader.getPhone())
                .param("library_id", reader.getLibraryId())
                .param("id", reader.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Reader WHERE id = :id")
                .param("id", id)
                .update();
    }
}
