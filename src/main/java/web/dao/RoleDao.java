package web.dao;

import web.model.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> getRoles(Set<String> role);

    Role getRole(String name);

    Set<Role> findAllRoles();
}