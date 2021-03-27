package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepositories repositories;

    @Autowired
    public UserService(UserRepositories repositories) {
        this.repositories = repositories;
    }


    public User findByEmail(String email) {
        return repositories.findByEmail(email);
    }


    public Optional<User> findById(Long id) {
        return repositories.findById(id);
    }


    public List<User> findAll() {
        return repositories.findAll();
    }


    public void saveUser(User user) {
        repositories.save(user);
    }


    public void deleteById(Long id) {
        repositories.deleteById(id);
    }


    public void update(User user) {
        repositories.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repositories.findByEmail(s);

    }
}