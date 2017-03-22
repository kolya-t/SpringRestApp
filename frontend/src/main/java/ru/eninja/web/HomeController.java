package ru.eninja.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.eninja.domain.User;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index", "/home"}, method = GET)
    public String home() {
//        RestTemplate restTemplate = new RestTemplate();
//        User user = restTemplate.getForObject("http://localhost/api/users/1", User.class);
        return "index";
    }
}
