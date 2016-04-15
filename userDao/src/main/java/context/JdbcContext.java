package context;

import connection.ConnectionMaker;
import model.User;
import statement.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    public final ConnectionMaker connectionMaker;

    public JdbcContext(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User jdbcContextWithStatementStrategyForQuery(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection conection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            conection = connectionMaker.getConnection();

            statement = statementStrategy.makeStatement(conection);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (conection != null)
                try {
                    conection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return user;
    }

    public Long jdbcContextWithStateStrategyForInsert(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection conection = null;
        PreparedStatement statement = null;
        Long id = null;
        try {
            conection = connectionMaker.getConnection();

            statement = statementStrategy.makeStatement(conection);

            statement.executeUpdate();

            id = getLastInsertId(conection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (conection != null)
                try {
                    conection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return id;
    }

    public void jdbcContextWithStatementForStateStrategy(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection conection = null;
        PreparedStatement statement = null;
        try {
            conection = connectionMaker.getConnection();


            statement = statementStrategy.makeStatement(conection);


            statement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (conection != null)
                try {
                    conection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public Long getLastInsertId(Connection conection) throws SQLException {
        PreparedStatement lastInsertIdStatement = conection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = lastInsertIdStatement.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);


        resultSet.close();
        lastInsertIdStatement.close();
        return id;
    }
}