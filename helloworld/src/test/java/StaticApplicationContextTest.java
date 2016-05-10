import kr.ac.jejunu.Hello;
import kr.ac.jejunu.HelloImpl;
import kr.ac.jejunu.HelloPerson;
import kr.ac.jejunu.HelloPersonImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by HSH on 2016. 5. 9..
 */
public class StaticApplicationContextTest {

    private StaticApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello", HelloImpl.class);
    }

    @Test
    public void applicationContext() {
        Hello hello = applicationContext.getBean("hello", Hello.class);
        assertThat(hello.sayHello(), is("kr.ac.jejunu.Hello~"));
    }

    @Test
    public void applicatoinContextUsingBeanDefinition() {
        BeanDefinition beanDefinition = new RootBeanDefinition(HelloPersonImpl.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", "현승호");
        beanDefinition.getPropertyValues().addPropertyValue("hello", new RuntimeBeanReference("hello"));
        applicationContext.registerBeanDefinition("helloPerson", beanDefinition);
        HelloPerson helloPerson = applicationContext.getBean("helloPerson", HelloPerson.class);
        assertThat(helloPerson.sayHello(), is("kr.ac.jejunu.Hello~ 현승호"));
    }
}
