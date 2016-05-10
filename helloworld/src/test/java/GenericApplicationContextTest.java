import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by HSH on 2016. 5. 10..
 */
public class GenericApplicationContextTest {

    @Test
    public void xmlGenericApplicationContext(){
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("applicationContext.xml");
        applicationContext.refresh();
        HelloPerson helloPerson = applicationContext.getBean("helloPerson", HelloPerson.class);
        assertThat(helloPerson.sayHello(), is("Hello~ 현승호"));
    }
}
