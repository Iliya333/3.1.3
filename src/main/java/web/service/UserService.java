package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User getById(Long id);

    Iterable<User> getAllUsers();

     User saveUser(User user);

    void deleteById(Long id);

    void update(User user);

    UserDetails loadUserByUsername(String s);
}
