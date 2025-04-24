package org.example.database_lib.repository;

import org.example.database_lib.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublicationDao implements DaoInterface<Publication> {
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
    public int create(Publication publication) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Publication (title, publication_year, storage_hall_number, " +
                        "shelf, rack, loan_flag, loan_period_days, " +
                        "library_id, publication_type_id) " +
                        "VALUES (:title, :publication_year, :storage_hall_number, " +
                        ":shelf, :rack, :loan_flag, :loan_period_days, " +
                        ":library_id, :publication_type_id)")
                .param(publication.getTitle())
                .param(publication.getPublicationYear())
                .param(publication.getStorageHallNumber())
                .param(publication.getShelf())
                .param( publication.getRack())
                .param(publication.getLoanFlag())
                .param(publication.getLoanPeriodDays())
                .param(publication.getLibraryId())
                .param(publication.getPublicationTypeId())
                .update(keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Publication> findAll() {
        return jdbcClient.sql("SELECT * FROM Publication").query(rowMapper).list();
    }

    @Override
    public Optional<Publication> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Publication l WHERE l.id = :id")
                .param(id)
                .query(rowMapper).optional();
    }

    @Override
    public int update(Publication publication) {
        return jdbcClient.sql("UPDATE Publication " +
                        "SET title = :title, publication_year = :publication_year, storage_hall_number = :storage_hall_number, " +
                        "shelf = :shelf, rack = :rack, loan_flag = :loan_flag, " +
                        "loan_period_days = :loan_period_days, library_id = :library_id, " +
                        "publication_type_id = :publication_type_id " +
                        "WHERE id = :id")
                .param(publication.getId())
                .param(publication.getTitle())
                .param(publication.getPublicationYear())
                .param(publication.getStorageHallNumber())
                .param(publication.getShelf())
                .param( publication.getRack())
                .param(publication.getLoanFlag())
                .param(publication.getLoanPeriodDays())
                .param(publication.getLibraryId())
                .param(publication.getPublicationTypeId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Publication l WHERE l.id = :id")
                .param(id)
                .update();
    }
}
