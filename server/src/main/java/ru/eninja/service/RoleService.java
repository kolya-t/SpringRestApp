package ru.eninja.service;


import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;
import ru.eninja.domain.Role;

import java.util.List;


@Service
public interface RoleService {

    /**
     * Saves role to table
     *
     * @param role role to save
     * @return id of saved role
     */
    Role saveRole(Role role);

    /**
     * Saves collection of roles to table
     *
     * @param roles role collection
     * @return saved collection
     */
    Iterable<? extends Role> saveRoles(Iterable<? extends Role> roles);

    /**
     * Finds and returns role by id
     *
     * @param id role id
     * @return role by id
     */
    Role getRoleById(Integer id);

    Role getRoleByAuthority(String authority);

    /**
     * Finds and returns role list
     *
     * @return role list
     */
    List<Role> getRoleList();

    /**
     * Returns {@code true} if role exists
     *
     * @param id role id
     * @return {@code true} if role exists and {@code false} otherwise
     */
    boolean exists(@NotNull Integer id);

    /**
     * @return roles count
     */
    long count();

    /**
     * Deletes role by id
     *
     * @param id role id
     */
    void deleteRole(Integer id);

    /**
     * Deleted role
     *
     * @param role role to delete
     */
    void deleteRole(Role role);

    /**
     * Deletes a collection of roles
     *
     * @param roles role collection to delete
     */
    void deleteRoles(Iterable<? extends Role> roles);

    /**
     * Deletes all roles from table
     */
    void deleteAll();
}
