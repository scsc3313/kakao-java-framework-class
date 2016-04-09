package statement;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 8..
 */
public class UpdateUserStatementStateStrategy implements StatementStrategy {
    private User user;

    public UpdateUserStatementStateStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setLong(3, user.getId());
        return statement;
    }
}
