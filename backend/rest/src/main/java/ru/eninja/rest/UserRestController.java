package ru.eninja.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eninja.domain.User;
import ru.eninja.rest.exceptions.ResourceNotFoundException;
import ru.eninja.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public User getUser(@PathVariable("id") long id, HttpServletResponse response) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        response.setHeader("Location", "/api/users/" + id);
        return user;
    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseStatus(NO_CONTENT)
    public void putUser(@PathVariable("id") long id,
                        @RequestBody @Valid User user,
                        HttpServletResponse response) {

        userService.saveUser(user);
        response.setHeader("Location", "/api/users/" + id);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public User createUser(@RequestBody @Valid User user,
                           BindingResult result,
                           HttpServletResponse response) throws BindException {

        if (result.hasErrors()) {
            throw new BindException(result);
        }

        userService.saveUser(user);
        response.setHeader("Location", "/api/users/" + user.getId());
        return user;
    }
}
