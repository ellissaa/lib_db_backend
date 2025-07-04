package org.example.database_lib.repository;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkDao implements DaoInterface<Work, Long> {
    private final JdbcClient jdbcClient;
    private final RowMapper<Work> rowMapper;

    @Autowired
    public WorkDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.rowMapper = (rs, rowNum) -> new Work(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getInt("creation_year"),
                rs.getString("genre")
        );
    }

    @Override
    public Work create(Work work) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO Work (title, creation_year, genre) " +
                        "VALUES (:title, :creation_year, :genre)")
                .param("title", work.getTitle())
                .param("creation_year", work.getCreationYear())
                .param("genre", work.getGenre())
                .update(keyHolder);
        return work;
    }

    @Override
    public List<Work> findAll() {
        return jdbcClient.sql("SELECT * FROM Work").query(rowMapper).list();
    }

    @Override
    public Optional<Work> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM Work WHERE id = :id")
                .param("id", id)
                .query(rowMapper).optional();
    }

    public List<Work> findMostPopular() {
        return jdbcClient.sql("with loancount(id, count) as (" +
                        "    select w.id, count(*) from loanjournal j " +
                        "    join copy c on j.copy_id = c.id " +
                        "    join publication p on c.publication_id = p.id " +
                        "    join publication_work pw on p.id = pw.publication_id " +
                        "    join work w on pw.work_id = w.id " +
                        "    group by w.id" +
                        ")" +
                        "select w2.* from loancount lc " +
                        "join work w2 on w2.id = lc.id " +
                        "where lc.count in (" +
                        "    select lc1.count from loancount lc1 " +
                        "    order by lc1.count desc " +
                        "    limit 2" +
                        ") " +
                        "order by lc.count desc")
                .query(rowMapper).list();
    }

    @Override
    public int update(Work work) {
        return jdbcClient.sql("UPDATE Work " +
                        "SET title = :title, creation_year = :creation_year, genre = :genre " +
                        "WHERE id = :id")
                .param("title", work.getTitle())
                .param("creation_year", work.getCreationYear())
                .param("genre", work.getGenre())
                .param("id", work.getId())
                .update();
    }

    @Override
    public int delete(Long id) {
        return jdbcClient.sql("DELETE from Work WHERE id = :id")
                .param("id", id)
                .update();
    }
}
