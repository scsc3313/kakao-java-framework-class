import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * Created by HSH on 2016. 3. 25..
 */
public class UserDaoTest {

    @Test
    public void get() throws SQLException, ClassNotFoundException {
//        UserDao userDao = new UserDao(new SimpleConnectionMaker());

        UserDao userDao = new DaoFactory().getUserDao();

        Long id = 1L;
        String name = "현승호";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {

        User user = new User();

        String name = "홍길동";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

//        UserDao userDao = new UserDao(new SimpleConnectionMaker());
        UserDao userDao = new DaoFactory().getUserDao();
        Long id = userDao.add(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId() , is(id));
        assertThat(resultUser.getName() , is(name));
        assertThat(resultUser.getPassword() , is(password));

    }

}

