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

            String sql = "select * from userinfo where id = ?";
            statement = conection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();
            resultSet.next();

            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
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

            String sql = "insert into userinfo (name, password) values (?, ?)";
            statement = conection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
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
