import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 1..
 */
public interface ConnectionMaker {

    Connection getConnection() throws ClassNotFoundException, SQLException;
}
