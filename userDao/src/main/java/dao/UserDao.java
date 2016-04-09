package dao;

import connection.ConnectionMaker;
import model.User;
import statement.*;

import java.sql.*;

/**
 * Created by HSH on 2016. 3. 25..
 */
public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {

        Connection conection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            conection = connectionMaker.getConnection();

            StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
            statement = statementStrategy.makeStatement(conection);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
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
            if(resultSet != null)
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
            if(conection != null)
                try {
                    conection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        Connection conection = null;
        PreparedStatement statement = null;
        Long id = null;
        try {
            conection = connectionMaker.getConnection();

            StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
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
            if(statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(conection != null)
                try {
                    conection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return id;
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContextWithStatementForStateStrategy(statementStrategy);

    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatementStateStrategy(user);
        jdbcContextWithStatementForStateStrategy(statementStrategy);
    }

    private void jdbcContextWithStatementForStateStrategy(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
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
            if(statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(conection != null)
                try {
                    conection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private Long getLastInsertId(Connection conection) throws SQLException {
        PreparedStatement lastInsertIdStatement = conection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = lastInsertIdStatement.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);


        resultSet.close();
        lastInsertIdStatement.close();
        return id;
    }
}
