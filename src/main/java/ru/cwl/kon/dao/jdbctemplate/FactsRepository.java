package ru.cwl.kon.dao.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.cwl.kon.model.Fact;
import ru.cwl.kon.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vad on 07.02.2016 10:18
 * 4kon
 */
public class FactsRepository implements Repository<Fact, Integer> {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Fact> rowMapper;

    public FactsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        rowMapper = new RowMapper<Fact>() {
            @Override
            public Fact mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fact f = new Fact();
                f.setId(rs.getInt("FACT_ID"));
                f.setUser("new User()");
                rs.getInt("USER_ID");
                rs.getInt("ACCOUNT_ID");
                rs.getInt("CATEGORY_ID");
                //f.setAmount(rs.getDouble("AMOUNT"));
                f.setDescription(rs.getString("DESCRIPTION"));

                return f;
            }
        };
    }

    public Fact create(Fact f) {
        final String sql = "INSERT INTO PUBLIC.FACTS (USER_ID, DATE, AMOUNT, ACCOUNT_ID, CATEGORY_ID, DESCRIPTION)" +
                " VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                "f.getUser().getId()", f.getDate(), f.getAmount(),
                1, 1, f.getDescription());

        return f;
    }

    public Fact read(Integer id) {
        return jdbcTemplate.queryForObject("select * from FACTS WHERE FACT_ID=?", rowMapper, id);
    }

    public Fact update(Fact fact) {
        // TODO
        return fact;
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM FACTS WHERE FACT_ID = ?", id);
    }

    public List<Fact> readAll() {
        List<Fact> results = jdbcTemplate.query("select * from FACTS", rowMapper);
        return results;
    }
}
