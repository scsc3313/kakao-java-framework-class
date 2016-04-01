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

        Connection conection = connectionMaker.getConnection();

        String sql = "select * from userinfo where id = ?";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        statement.close();
        conection.close();

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        Connection conection = connectionMaker.getConnection();

        String sql = "insert into userinfo (name, password) values (?, ?)";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();

        Long id = getLastInsertId(conection);

        statement.close();
        conection.close();

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
