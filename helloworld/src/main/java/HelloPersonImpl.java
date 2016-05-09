/**
 * Created by HSH on 2016. 5. 9..
 */
public class HelloPersonImpl implements HelloPerson {

    private Hello hello;
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
