package dao;

import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;

/**
 * Created by HSH on 2016. 3. 25..
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public User get(Long id) throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo where id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            });
        } catch (EmptyResultDataAccessException e) {
        }

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo (name, password) values (?, ?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                return statement;
            }
        };
        jdbcTemplate.update(psc,generatedKeyHolder);
        return (Long) generatedKeyHolder.getKey();
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);

    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
