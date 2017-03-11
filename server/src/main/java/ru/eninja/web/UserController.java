package ru.eninja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eninja.domain.User;
import ru.eninja.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseStatus(NO_CONTENT)
    public void putUser(@PathVariable long id, @RequestBody @Valid User user) {
        userService.saveUser(user);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public User createUser(@RequestBody @Valid User user, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }

        userService.saveUser(user);
        return user;
    }
}
