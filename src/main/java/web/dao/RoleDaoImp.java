package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;
@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Set<Role> getRoles(Set<String> role) {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r WHERE r.name in (:role)")
                .setParameter("role", role)
                .getResultList());
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r from Role r", Role.class)
                .getResultList());
    }

    @Override
    public Role getRole(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :roleName", Role.class)
                .setParameter("roleName", name)
                .setMaxResults(1)
                .getSingleResult();
    }
}