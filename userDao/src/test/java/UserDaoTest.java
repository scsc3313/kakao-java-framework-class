import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by HSH on 2016. 3. 25..
 */
public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext =
                new GenericXmlApplicationContext("daoFactory.xml");
//        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = (UserDao) applicationContext.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "현승호";
        String password = "1234";

        User user = userDao.get(id);

        validate(id, name, password, user);
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {

        User user = new User();

        String name = "홍길동";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

//        UserDao userDao = new UserDao(new SimpleConnectionMaker());
        Long id = userDao.add(user);

        User resultUser = userDao.get(id);
        validate(id, name, password, resultUser);

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {

        User user = new User();

        String name = "홍길동";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

//        UserDao userDao = new UserDao(new SimpleConnectionMaker());
        Long id = userDao.add(user);

        userDao.delete(id);

        User resultUser = userDao.get(id);
        assertThat(resultUser, nullValue());
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {

        User user = new User();

        String name = "홍길동";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

//        UserDao userDao = new UserDao(new SimpleConnectionMaker());
        Long id = userDao.add(user);

        name = "김철수";
        password = "1111";

        user.setName(name);
        user.setPassword(password);
        user.setId(id);

        userDao.update(user);

        User resultUser = userDao.get(id);
        validate(id, name, password, resultUser);
    }


    private void validate(Long id, String name, String password, User user) {
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

}

