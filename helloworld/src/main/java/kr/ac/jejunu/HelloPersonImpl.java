package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by HSH on 2016. 5. 9..
 */
@Component
public class HelloPersonImpl implements HelloPerson {

    @Autowired
    private Hello hello;
    @Value("현승호")
    private String name;

    public Hello getHello() {
        return hello;
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String sayHello() {
        return hello.sayHello() + " " + name;
    }
}
