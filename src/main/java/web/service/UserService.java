package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByFirstName(String firstName);

    User getUserId(Long id);

    List<User> listUser();

    void saveUser(User user);

    void deleteById(Long id);

    void updateUser(User user);




}