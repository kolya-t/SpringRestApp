package ru.eninja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.eninja.domain.User;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequestMapping("/users")
@PropertySource("classpath:api.properties")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = GET)
    @SuppressWarnings("unchecked")
    public String showUserList(Model model,
                               @Value("${api.users.getAll}") String apiUrl) {
        List<User> userList = restTemplate.getForObject(apiUrl, List.class);
        model.addAttribute("userList", userList);
        return "users/list";
    }

    @RequestMapping(value = "/edit", method = GET)
    public String showUserEdit(@RequestParam(value = "id") long id,
                               Model model,
                               @Value("${api.users.get}") String apiUrl) {
        User user = restTemplate.getForObject(apiUrl, User.class, id);
        model.addAttribute(user);
        return "users/edit";
    }

    @RequestMapping(value = "/add", method = GET)
    public String showUserAdd(Model model) {
        model.addAttribute(new User());
        return "users/edit";
    }
}
