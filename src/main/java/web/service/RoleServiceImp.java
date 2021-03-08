package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService{

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoles(Set<String> role) {
        return roleDao.getRoles(role);
    }

    @Override
    public Role getRole(String name) {
        return roleDao.getRole(name);
    }

    @Override
    public Set<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }
}