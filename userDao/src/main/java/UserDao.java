import java.sql.*;

/**
 * Created by HSH on 2016. 3. 25..
 */
public class UserDao {
    public User get(Long id) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu", "jeju", "jejupw");

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
}
