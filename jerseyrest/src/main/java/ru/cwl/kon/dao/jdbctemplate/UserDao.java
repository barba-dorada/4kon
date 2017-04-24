package ru.cwl.kon.dao.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.cwl.kon.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vad on 06.02.2016 21:14
 * 4kon
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                u.setName(u.getLogin());
                return u;
            }
        };
    }

    public List<User> getAll() {
        List<User> results = jdbcTemplate.query("select * from USERS", rowMapper);
        return results;
    }

    public User getById(int id) {
        return jdbcTemplate.queryForObject("select * from USERS WHERE user_id=?", rowMapper, id);
    }

    public User getByLogin(String login) {
        return jdbcTemplate.queryForObject("select * from USERS WHERE login=?", rowMapper, login);
    }

    public User create(User user) {
        //System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
        jdbcTemplate.update(
                "INSERT INTO USERS(LOGIN,PASSWORD) values(?,?)",
                user.getLogin(), user.getPassword());
        return getByLogin(user.getLogin());
    }

    public User update(User user) {
        jdbcTemplate.update(
                "UPDATE USERS SET LOGIN=?, PASSWORD=? WHERE USER_ID = ?",
                user.getLogin(), user.getPassword(), user.getId());
        return getById(user.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM USERS WHERE USER_ID = ?", id);
    }
}
