package web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repositories.UserRepositories;

@Service
@Slf4j
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepositories repositories;

    @Autowired
    public UserServiceImp(UserRepositories repositories) {
        this.repositories = repositories;

    }


    public User findByEmail(String email) {
        return repositories.findByEmail(email);
    }


    public User getById(Long id) {
        return repositories.findById(id).orElse(new User());
    }


    public Iterable<User> getAllUsers() {
        return repositories.findAll();
    }

    public User saveUser(User user) {
        repositories.save(user);
        return user;
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