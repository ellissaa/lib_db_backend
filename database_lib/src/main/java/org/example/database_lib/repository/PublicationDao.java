package org.example.database_lib.repository;

import org.example.database_lib.model.Publication;
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
public class PublicationDao implements DaoInterface<Publication, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Publication> rowMapper;

    @Autowired
    public PublicationDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Publication(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getInt("publication_year"),
                rs.getInt("storage_hall_number"),
                rs.getInt("shelf"),
                rs.getInt("rack"),
                rs.getBoolean("loan_flag"),
                rs.getInt("loan_period_days"),
                rs.getLong("library_id"),
                rs.getLong("publication_type_id")
        );
    }

    @Override
    public Publication create(Publication publication) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Publication (title, publication_year, storage_hall_number, " +
                        "shelf, rack, loan_flag, loan_period_days, " +
                        "library_id, publication_type_id) " +
                        "VALUES (:title, :publication_year, :storage_hall_number, " +
                        ":shelf, :rack, :loan_flag, :loan_period_days, " +
                        ":library_id, :publication_type_id)")
                .param("title", publication.getTitle())
                .param("publication_year", publication.getPublicationYear())
                .param("storage_hall_number", publication.getStorageHallNumber())
                .param("shelf", publication.getShelf())
                .param("rack", publication.getRack())
                .param("loan_flag", publication.getLoanFlag())
                .param("loan_period_days", publication.getLoanPeriodDays())
                .param("library_id", publication.getLibraryId())
                .param("publication_type_id", publication.getPublicationTypeId())
                .update(keyHolder);
        return publication;
    }

    @Override
    public List<Publication> findAll() {
        return jdbcClient.sql("SELECT * FROM Publication").query(rowMapper).list();
    }

    @Override
    public Optional<Publication> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Publication WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Publication> findByReaderReg(String start, String end, Long readerId) {
        return jdbcClient.sql("select distinct p.* from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "join copy c on j.copy_id = c.id " +
                        "join publication p on c.publication_id = p.id " +
                        "where r.library_id = p.library_id " +
                        "and j.loan_date >= cast(:start as date) " +
                        "and j.loan_date <= cast(:end as date) " +
                        "and r.id = :readerId " +
                        "order by p.id")
                .param("start", start)
                .param("end", end)
                .param("readerId", readerId)
                .query(rowMapper).list();
    }

    public List<Publication> findByReaderNotReg(String start, String end, Long readerId) {
        return jdbcClient.sql("select distinct p.* from reader r " +
                        "join loanjournal j on r.id = j.reader_id " +
                        "join copy c on j.copy_id = c.id " +
                        "join publication p on c.publication_id = p.id " +
                        "where r.library_id != p.library_id " +
                        "and j.loan_date >= cast(:start as date) " +
                        "and j.loan_date <= cast(:end as date) " +
                        "and r.id = :readerId " +
                        "order by p.id")
                .param("start", start)
                .param("end", end)
                .param("readerId", readerId)
                .query(rowMapper).list();
    }

    public List<Publication> findByShelf(Long lib, Long hall, Long rack, Long shelf) {
        return jdbcClient.sql("select p.* from publication p " +
                        "join copy c on p.id = c.publication_id " +
                        "join loanjournal j on c.id = j.copy_id " +
                        "where j.actual_return_date is null " +
                        "and p.library_id = :lib " +
                        "and p.storage_hall_number = :hall " +
                        "and p.rack = :rack " +
                        "and p.shelf = :shelf " +
                        "order by p.id")
                .param("lib", lib)
                .param("hall", hall)
                .param("rack", rack)
                .param("shelf", shelf)
                .query(rowMapper).list();
    }

    public List<Publication> findByWork(String work) {
        return jdbcClient.sql("select distinct p.* from publication p " +
                        "join publication_work pw on p.id = pw.publication_id " +
                        "join work w on pw.work_id = w.id " +
                        "where lower(w.title) = lower(:work) " +
                        "order by p.id")
                .param("work", work)
                .query(rowMapper).list();
    }

    public List<Publication> findByAuthor(Long author) {
        return jdbcClient.sql("select distinct p.* from publication p " +
                        "join publication_work pw on p.id = pw.publication_id " +
                        "join work w on pw.work_id = w.id " +
                        "join work_author wa on w.id = wa.work_id " +
                        "join author a on wa.author_id = a.id " +
                        "where a.id = :author " +
                        "order by p.id")
                .param("author", author)
                .query(rowMapper).list();
    }

    @Override
    public int update(Publication publication) {
        return jdbcClient.sql("UPDATE Publication " +
                        "SET title = :title, publication_year = :publication_year, storage_hall_number = :storage_hall_number, " +
                        "shelf = :shelf, rack = :rack, loan_flag = :loan_flag, " +
                        "loan_period_days = :loan_period_days, library_id = :library_id, " +
                        "publication_type_id = :publication_type_id " +
                        "WHERE id = :id")
                .param("title", publication.getTitle())
                .param("publication_year", publication.getPublicationYear())
                .param("storage_hall_number", publication.getStorageHallNumber())
                .param("shelf", publication.getShelf())
                .param("rack", publication.getRack())
                .param("loan_flag", publication.getLoanFlag())
                .param("loan_period_days", publication.getLoanPeriodDays())
                .param("library_id", publication.getLibraryId())
                .param("publication_type_id", publication.getPublicationTypeId())
                .param("id", publication.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Publication WHERE id = :id")
                .param("id", id)
                .update();
    }
}
