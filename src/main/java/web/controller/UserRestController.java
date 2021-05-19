package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleServiceImp;
import web.service.UserServiceImp;

@RestController
public class UserRestController {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public UserRestController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/getAuthorizedUser")
    public ResponseEntity<User> getAuthorizedUser(Authentication authentication) {
        User user = userServiceImp.findByEmail(authentication.getName());
        return ResponseEntity.ok(user);
    }


    @GetMapping(value = "/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userServiceImp.getAllUsers());
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok(roleServiceImp.getAllRoles());
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userServiceImp.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        userServiceImp.saveUser(user);
        return ResponseEntity.unprocessableEntity().body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userServiceImp.update(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userServiceImp.deleteById(id);
        return ResponseEntity.ok().body(id);
    }
}


