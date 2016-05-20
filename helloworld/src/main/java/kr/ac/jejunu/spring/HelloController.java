package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HSH on 2016. 5. 19..
 */
@Controller
public class HelloController {
    @RequestMapping("/spring/hello")
    public void hello(Model model){
        model.addAttribute("Request Mapping!");
        model.addAttribute(1);
        model.addAttribute("name", "현승호");
    }
}
