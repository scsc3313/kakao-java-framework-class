import kr.ac.jejunu.Hello;
import kr.ac.jejunu.HelloPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by HSH on 2016. 5. 10..
 */
public class AnnotationConfigApplicationContextTest {

    private ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu");
    }

    @Test
    public void applicationContext(){
        Hello hello = applicationContext.getBean("hello", Hello.class);
        assertThat(hello.sayHello(), is("kr.ac.jejunu.Hello~"));
    }

    @Test
    public void applicationContextUsingClass(){
        Hello hello = applicationContext.getBean(Hello.class);
        assertThat(hello.sayHello(), is("kr.ac.jejunu.Hello~"));
    }

    @Test
    public void applicationContextUsingDi(){
        HelloPerson helloPerson = applicationContext.getBean(HelloPerson.class);
        assertThat(helloPerson.sayHello(), is("kr.ac.jejunu.Hello~ 현승호"));
    }
}
