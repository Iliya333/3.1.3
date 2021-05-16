package web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repositories.RoleRepositories;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class RoleService {

    private final RoleRepositories repositories;


    @Autowired
    public RoleService(RoleRepositories repositories) {
        this.repositories = repositories;

    }

    public Set<Role> getAllRoles() {
        Iterable<Role> iterable = repositories.findAll();
        Set<Role> set = new HashSet<>();
        iterable.forEach(role -> set.add(role));
        return set;
    }

}