package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HSH on 2016. 5. 19..
 */
@Controller
public class HelloController {
    @RequestMapping("/spring/hello")
    public void hello(){
        
    }
}
