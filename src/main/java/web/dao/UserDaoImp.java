package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final RoleDao roleDao;

    @Autowired
    public UserDaoImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByFirstName(String firstName) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.firstName = :firstName", User.class);
        return query.setParameter("firstName", firstName).getSingleResult();
    }

    @Override
    public User getUserId(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void saveUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role dbRole : roleDao.findAllRoles()) {
                if (dbRole.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(dbRole.getId());
                }
            }
        }
        entityManager.persist(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void updateUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role dbRole : roleDao.findAllRoles()) {
                if (dbRole.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(dbRole.getId());
                }
            }
        }
        entityManager.merge(user);
    }

}
