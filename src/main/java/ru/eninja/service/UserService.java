package ru.eninja.service;

import com.sun.istack.internal.NotNull;
import ru.eninja.domain.User;

import java.util.List;


/**
 * Interface of abstract user service. Defines operations on users
 */
public interface UserService {

    /**
     * Saves user to table
     *
     * @param user user to save
     * @return id of saved user
     */
    User saveUser(User user);

    /**
     * Saves collection of Users to table
     *
     * @param users user collection
     * @return saved collection
     */
    Iterable<? extends User> saveUsers(Iterable<? extends User> users);

    /**
     * Finds and returns user by id
     *
     * @param id user id
     * @return user by id
     */
    User getUserById(Long id);

    /**
     * Finds and returns user list
     *
     * @return user list
     */
    List<User> getUserList();

    /**
     * Returns {@code true} if user exists
     *
     * @param id user id
     * @return {@code true} if user exists and {@code false} otherwise
     */
    boolean exists(@NotNull Long id);

    /**
     * @return users count
     */
    long count();

    /**
     * Deletes user by id
     *
     * @param id user id
     */
    void deleteUser(Long id);

    /**
     * Deleted user
     *
     * @param user user to delete
     */
    void deleteUser(User user);

    /**
     * Deletes a collection of users
     *
     * @param users user collection to delete
     */
    void deleteUsers(Iterable<? extends User> users);

    /**
     * Deletes all users from table
     */
    void deleteAll();

    /**
     * Return user by login
     *
     * @param login user login
     * @return user by login
     */
    User getUserByLogin(String login);

    /**
     * Return user by email
     *
     * @param email user email
     * @return user by email
     */
    User getUserByEmail(String email);

    /**
     * Returns user by login and password
     *
     * @param login    user login
     * @param password user password
     * @return user by login and password
     */
    User getUserByLoginAndPassword(String login, String password);
}
