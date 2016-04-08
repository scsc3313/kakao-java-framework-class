import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 8..
 */
public class GetUserStatementStrategy implements StatementStrategy {
    private Long id;

    public GetUserStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        return statement;
    }
}
