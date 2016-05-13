package kr.ac.jejunu.hello;

import org.springframework.stereotype.Component;

/**
 * Created by HSH on 2016. 5. 9..
 */
@Component("hello")
public class HelloImpl implements Hello{
    @Override
    public String sayHello() {
        return "kr.ac.jejunu.hello.Hello~";
    }
}
