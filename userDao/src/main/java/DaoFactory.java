/**
 * Created by HSH on 2016. 4. 1..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(new SimpleConnectionMaker());
    }
}