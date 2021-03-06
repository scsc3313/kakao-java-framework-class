package kr.ac.jejunu.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hyh0408 on 2016. 5. 12..
 */
@Controller
@RequestMapping("/spring")
@SessionAttributes("helloModel")
public class HelloController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public void hello(Model model) {
        model.addAttribute("hello world!!");
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET)
    public String hello2(@PathVariable String name, Model model) {
        model.addAttribute("hello world!! " + name);
        return "/spring/hello";
    }

    @RequestMapping("/{hello:[a-z]+}/{name:[a-z]+}")
    public String hello3(@PathVariable String hello, @PathVariable String name, Model model) {
        model.addAttribute(hello + " !! " + name);
        return "/spring/hello";
    }

    @RequestMapping("/hi/{hello}")
    public String hello4(@PathVariable String hello, @MatrixVariable String name,
                         Model model) {
        model.addAttribute(hello + " !! " + name);
        return "/spring/hello";
    }

    @RequestMapping("/helloworld")
    public String hello5(@RequestParam String hello, @RequestParam String name, Model model) {
        model.addAttribute(hello + " " + name);
        return "/spring/hello";
    }

    @RequestMapping("/hellomodel")
    public String hello6(HelloModel model) {
        logger.info("********" + model.getHello() + " " + model.getName() + "********");
        return "/spring/hellomodel";
    }

    @RequestMapping("/hellocookie")
    public String hello7(HelloModel model, @CookieValue(value = "name", defaultValue = "Seungho") String name, HttpServletResponse response) {
        model.setName(name);
        if ("Seungho".equals(name))
            response.addCookie(new Cookie("name", "HyunSeungho"));
        else
            response.addCookie(new Cookie("name", "Seungho"));
        return "/spring/hellomodel";
    }

    @RequestMapping("/hellosession")
    public String hello8(HelloModel model, HttpSession session) {

        String name = (String) session.getAttribute("name");
        model.setName(name);
        if ("Seungho".equals(name))
            session.setAttribute("name", "HyunSeungho");
        else
            session.setAttribute("name", "Seungho");
        return "/spring/hellomodel";
    }

    @ModelAttribute("helloModel")
    public HelloModel model() {
        return new HelloModel();
    }

    @RequestMapping("/sessionattribute")
    public String hello9(HelloModel model) {
        model.setHello("Hello~");
        model.setName("SeungHo");
        return "/spring/hellomodel";
    }

    @RequestMapping("/sessionattributevalue")
    public String hello10(HelloModel model){
        return "/spring/hellomodel";
    }

    @RequestMapping("/returnmodel")
    public HelloModel hello11(){
        HelloModel model = new HelloModel();
        model.setHello("hi");
        model.setName("Seungho");
        return model;
    }

}
