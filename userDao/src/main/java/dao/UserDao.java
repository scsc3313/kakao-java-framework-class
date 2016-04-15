package dao;

import context.JdbcContext;
import model.User;
import statement.*;

import java.sql.SQLException;

/**
 * Created by HSH on 2016. 3. 25..
 */
public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForQuery(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStateStrategyForInsert(statementStrategy);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementForStateStrategy(statementStrategy);

    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatementStateStrategy(user);
        jdbcContext.jdbcContextWithStatementForStateStrategy(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
